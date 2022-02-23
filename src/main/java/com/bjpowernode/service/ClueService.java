package com.bjpowernode.service;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.User;

import java.util.List;

public interface ClueService {


    /**
     * 添加线索
     * @param clue 要添加的线索
     * @return true成功，false失败
     */
    boolean addClue(Clue clue);

    /**
     * 线索遍历
     * @return 所有线索
     */
    List<Clue> getAll();

    /**
     * 根据id查找线索
     * @param id 线索id
     * @return json格式的clue
     */
    String getClueById(String id);

    /**
     * 修改线索
     * @param clue 要修改的线索表
     * @return true成功，false失败
     */
    boolean updateClue(Clue clue);

    /**
     * 删除线索
     * @param id 要删除的线索表
     * @return true成功，false失败
     */
    boolean deleteClueById(String id);

}
