package com.bjpowernode.service.impl;

import com.bjpowernode.domain.*;
import com.bjpowernode.mapper.*;
import com.bjpowernode.service.ClueService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.DateTimeUtil;
import com.bjpowernode.utils.JSONObject;
import com.bjpowernode.utils.MD5Util;
import com.bjpowernode.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private TranMapper tranMapper;










    @Override
    public boolean addClue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        return clueMapper.addClue(clue) == 1;
    }

    @Override
    public List<Clue> getAll() {
        return clueMapper.getAll(new Clue());
    }

    @Override
    @ResponseBody
    public Clue getClueJsonById(String id) {
        return clueMapper.getClueJsonById(id);
    }


    @Override
    public Clue getClueById(String id) {
        return clueMapper.getClueById(id);
    }

    @Override
    public boolean updateClue(Clue clue) {
        clue.setEditTime(DateTimeUtil.getSysTime());
        return clueMapper.updateClue(clue) == 1;
    }

    @Override
    public boolean deleteClueById(String id) {
        return clueMapper.deleteClue(id) == 1;
    }

    @Override
    public PageInfo<Clue> splitPage(Clue clue,Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Clue> movies = clueMapper.getAll(clue);
        PageInfo<Clue> pageInfo = new PageInfo<> (movies);
        return pageInfo;
    }

    @Override
    public boolean addConvert(Tran tran, String clueId) {


        boolean flag=true;
        //(1) 获取到线索id，通过线索id获取线索对象（线索对象当中封装了线索的信息）
        Clue c=clueMapper.getClueById(clueId);

        String commpany=c.getCompany();
        //(2) 通过线索对象提取客户信息，当该客户不存在的时候，新建客户（根据公司的名称精确匹配，判断该客户是否存在！）

        Customer customer= customerMapper.getCustomerByName(commpany);

        if (customer==null){
            customer=new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateBy(tran.getCreateBy());
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setAddress(c.getAddress());
            customer.setWebsite(c.getWebsite());
            customer.setPhone(c.getPhone());
            customer.setContactSummary(c.getContactSummary());
            customer.setDescription(c.getDescription());
            customer.setNextContactTime(c.getNextContactTime());
            customer.setOwner(c.getOwner());
            customer.setName(commpany);

            int count=customerMapper.insert(customer);
            if (count!=1){
                flag=false;
            }

        }


        //(3) 通过线索对象提取联系人信息，保存联系人

        Contacts contacts=new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setFullname(c.getFullname());
        contacts.setCreateBy(tran.getCreateBy());
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        contacts.setAddress(c.getAddress());
        contacts.setContactSummary(c.getContactSummary());
        contacts.setDescription(c.getDescription());
        contacts.setNextContactTime(c.getNextContactTime());
        contacts.setOwner(c.getOwner());
        contacts.setAppellation(c.getAppellation());
        contacts.setEmail(c.getEmail());
        contacts.setJob(c.getJob());
        contacts.setSource(c.getSource());
        contacts.setMphone(c.getMphone());
        contacts.setCustomerId(customer.getId());

        int count1=contactsMapper.insert(contacts);
        if (count1!=1){
            flag=false;
        }


        //(4) 线索备注转换到客户备注以及联系人备注

        List<ClueRemark> clueRemarkList= clueRemarkMapper.getClueRemarkByIdAll(clueId);


        for (ClueRemark clueRemark:clueRemarkList){
            String note=clueRemark.getNoteContent();
            CustomerRemark customerRemark=new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateBy(tran.getCreateBy());
            customerRemark.setCreateTime(DateTimeUtil.getSysTime());
            customerRemark.setCustomerId(customer.getId());
            customerRemark.setNoteContent(note);
            customerRemark.setEditFlag("0");
            int count2=customerRemarkMapper.insert(customerRemark);
            if (count2!=1){
                flag=false;
            }

            ContactsRemark contactsRemark=new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setCreateBy(tran.getCreateBy());
            contactsRemark.setCreateTime(DateTimeUtil.getSysTime());
            contactsRemark.setContactsId(contacts.getId());
            contactsRemark.setNoteContent(note);
            contactsRemark.setEditFlag("0");
            int count3=contactsRemarkMapper.insert(contactsRemark);

            if (count3!=1){
                flag=false;
            }

        }


        //(5) “线索和市场活动”的关系转换到“联系人和市场活动”的关系
        List<ClueActivityRelation> clueActivityRelationList=clueActivityRelationMapper.selectByClueId(clueId);

        for (ClueActivityRelation clueActivityRelation:clueActivityRelationList){

            String activityId=clueActivityRelation.getActivityId();
            ContactsActivityRelation contactsActivityRelation=new ContactsActivityRelation();
            contactsActivityRelation.setActivityId(activityId);
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setContactsId(contacts.getId());
            int count4=contactsActivityRelationMapper.insert(contactsActivityRelation);
            if (count4!=1){
                flag=false;
            }

        }

        //(6) 如果有创建交易需求，创建一条交易
        if (tran!=null){
            tran.setSource(c.getSource());
            tran.setOwner(c.getOwner());
            tran.setNextContactTime(c.getNextContactTime());
            tran.setCustomerId(contacts.getId());
            tran.setContactSummary(c.getContactSummary());
            tran.setContactsId(contacts.getId());
            int count5=tranMapper.insert(tran);
            if (count5!=1){
                flag=false;
            }
            //(7) 如果创建了交易，则创建一条该交易下的交易历史
            TranHistory th=new TranHistory();
            th.setId(UUIDUtil.getUUID());
            th.setCreateBy(tran.getCreateBy());
            th.setCreateTime(DateTimeUtil.getSysTime());
            th.setExpectedDate(tran.getExpectedDate());
            th.setMoney(tran.getMoney());
            th.setTranId(tran.getId());
            th.setStage(tran.getStage());

            int count6=tranHistoryMapper.insert(th);
            if (count6!=1){
                flag=false;
            }

        }


        //(8) 删除线索备注

        for (ClueRemark clueRemark:clueRemarkList){

            int count7=clueRemarkMapper.delete(clueRemark);
            if (count7!=1){
                flag=false;
            }

        }

        //(9)  删除线索和市场活动的关系
        for (ClueActivityRelation clueActivityRelation:clueActivityRelationList){

            System.out.println(clueActivityRelation);
            int count8=clueActivityRelationMapper.delete(clueActivityRelation);
            if (count8!=1){
                flag=false;
            }

        }


        //(10) 删除线索

        int count9=clueMapper.delete(clueId);
        if (count9!=1){
            flag=false;
        }

        return flag;
    }



}
