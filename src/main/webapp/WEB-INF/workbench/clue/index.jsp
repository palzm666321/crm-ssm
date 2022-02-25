<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript"  src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript"  src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"  src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"  src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/interface/clueServiceImpl.js"></script>

<script type="text/javascript">

	$(function(){
		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "top-left"
		});

		$("#qx").click(function (){
			$("input[name=dx]").prop("checked",this.checked);
		})
		$("#body").on("click","input[name=dx]",function (){
			$("#qx").prop("checked",$("input[name=dx]:checked").length == $("input[name=dx]").length);
		})



	});



	//保存线索
	function save(){
		var clueMap={};
		clueMap.fullname=clueAddForm.createFullname.value;
		clueMap.contactSummary=clueAddForm.createContactSummary.value;
		clueMap.website=clueAddForm.createWebsite.value;
		clueMap.nextContactTime=clueAddForm.createNextContactTime.value;
		clueMap.address=clueAddForm.createAddress.value;
		clueMap.description=clueAddForm.createDescription.value;
		clueMap.phone=clueAddForm.createPhone.value;
		clueMap.mphone=clueAddForm.createMphone.value;
		clueMap.createBy="${user.name}";
		clueMap.state=clueAddForm.createState.value;
		clueMap.source=clueAddForm.createSource.value;
		clueMap.appellation=clueAddForm.createAppellation.value;
		clueMap.owner=clueAddForm.createOwner.value;
		clueMap.company=clueAddForm.createCompany.value;
		clueMap.job=clueAddForm.createJob.value;
		clueMap.email=clueAddForm.createEmail.value;
		clueServiceImpl.addClue(clueMap,function (data){
			if(data){
				alert("创建成功！！");
				window.location.href="clue/index.do";
			}else{
				alert("创建失败！！");
			}
		})
	}

    //修改线索前的页面加载
    function updateBtn(){
        var xz=$("input[name=dx]:checked");
        if (xz.length < 1){
            alert("请选择要修改的线索")
        }else if (xz.length > 1){
            alert("只能选择一个线索进行修改")
        }else{
            clueServiceImpl.getClueJsonById(xz.val(),function (data){
				clueUpdateForm.editSurname.value=data.fullname;
				clueUpdateForm.editContactSummary.value=data.contactSummary;
				clueUpdateForm.editWebsite.value=data.website;
				clueUpdateForm.editNextContactTime.value=data.nextContactTime;
				clueUpdateForm.editAddress.value=data.address;
				clueUpdateForm.editDescribe.value=data.description;
				clueUpdateForm.editPhone.value=data.phone;
				clueUpdateForm.editMphone.value=data.mphone;
				clueUpdateForm.editStatus.value=data.state;
				clueUpdateForm.editSource.value=data.source;
				clueUpdateForm.editCall.value=data.appellation;
				clueUpdateForm.editOwner.value=data.owner;
				clueUpdateForm.editCompany.value=data.company;
				clueUpdateForm.editJob.value=data.job;
				clueUpdateForm.editEmail.value=data.email;
				$("#editClueModal").modal("show")
			});
        }
    }

	//修改线索
    function update(){
		var clueMap={};
		clueMap.id=$("input[name=dx]:checked").val();
		clueMap.fullname=clueUpdateForm.editSurname.value;
		clueMap.contactSummary=clueUpdateForm.editContactSummary.value;
		clueMap.website=clueUpdateForm.editWebsite.value;
		clueMap.nextContactTime=clueUpdateForm.editNextContactTime.value;
		clueMap.address=clueUpdateForm.editAddress.value;
		clueMap.description=clueUpdateForm.editDescribe.value;
		clueMap.phone=clueUpdateForm.editPhone.value;
		clueMap.mphone=clueUpdateForm.editMphone.value;
		clueMap.editBy="${user.name}";
		clueMap.state=clueUpdateForm.editStatus.value;
		clueMap.source=clueUpdateForm.editSource.value;
		clueMap.appellation=clueUpdateForm.editCall.value;
		clueMap.owner=clueUpdateForm.editOwner.value;
		clueMap.company=clueUpdateForm.editCompany.value;
		clueMap.job=clueUpdateForm.editJob.value;
		clueMap.email=clueUpdateForm.editEmail.value;
		clueServiceImpl.updateClue(clueMap,function (data){
			if(data){
				alert("修改成功！！");
				window.location.href="clue/index.do";
			}else{
				alert("修改失败！！");
			}
		})


    }

	//删除线索
	function drop(){
		var xz=$("input[name=dx]:checked");
		if (xz.length < 1){
			alert("请选择要删除的线索")
		}else if (xz.length > 1){
			alert("只能选择一个线索进行删除")
		}else{
			clueServiceImpl.deleteClueById(xz.val(),function(data){
				if(data){
					alert("删除成功！！");
					window.location.href="clue/index.do";
				}else{
					alert("删除失败！！");
				}
			});
		}
	}


</script>
</head>
<body>

	<!-- 创建线索的模态窗口 -->
	<div class="modal fade" id="createClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建线索</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" name="clueAddForm">
						<div class="form-group">
							<label for="create-clueOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-clueOwner" name="createOwner">
									<option></option>
									<c:forEach items="${userAll}" var="a">
										<option value="${a.id}" ${user.id==a.id ? "selected":""}>${a.name}</option>
									</c:forEach>
								</select>
							</div>
							<label for="create-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-company" name="createCompany">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-call" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-call" name="createAppellation">
								  <option></option>
									<c:forEach items="${appellation}" var="a">
										<option value="${a.value}">${a.text}</option>
									</c:forEach>
								</select>
							</div>
							<label for="create-surname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-surname" name="createFullname">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-job" name="createJob">
							</div>
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email" name="createEmail">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone" name="createPhone">
							</div>
							<label for="create-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-website" name="createWebsite">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-mphone" name="createMphone">
							</div>
							<label for="create-status" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-status" name="createState">
								  <option></option>
									<c:forEach items="${clueState}" var="a">
										<option value="${a.value}">${a.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-source" name="createSource">
								  <option></option>
									<c:forEach items="${source}" var="a">
										<option value="${a.value}">${a.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">线索描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe" name="createDescription"></textarea>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>
						
						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="create-contactSummary" name="createContactSummary"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control time" id="create-nextContactTime" name="createNextContactTime">
								</div>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>
						
						<div style="position: relative;top: 20px;">
							<div class="form-group">
                                <label for="create-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="create-address" name="createAddress"></textarea>
                                </div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save()">保存</button>
						</div>

					</form>
					
				</div>

			</div>
		</div>
	</div>
	
	<!-- 修改线索的模态窗口 -->
	<div class="modal fade" id="editClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改线索</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" name="clueUpdateForm">
					
						<div class="form-group">
							<label for="edit-clueOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-clueOwner" name="editOwner">
                                    <c:forEach items="${userAll}" var="a">
                                        <option value="${a.id}">${a.name}</option>
                                    </c:forEach>
								</select>
							</div>
							<label for="edit-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-company" name="editCompany">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-call" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-call" name="editCall">
                                    <c:forEach items="${appellation}" var="a">
                                        <option value="${a.value}">${a.text}</option>
                                    </c:forEach>
								</select>
							</div>
							<label for="edit-surname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-surname" name="editSurname">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job" name="editJob">
							</div>
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email" name="editEmail">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone" name="editPhone">
							</div>
							<label for="edit-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website" name="editWebsite">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone" name="editMphone">
							</div>
							<label for="edit-status" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-status" name="editStatus">
                                    <c:forEach items="${clueState}" var="a">
                                        <option value="${a.value}">${a.text}</option>
                                    </c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-source" name="editSource">
                                    <c:forEach items="${source}" var="a">
                                        <option value="${a.value}">${a.text}</option>
                                    </c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe" name="editDescribe"></textarea>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>
						
						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="edit-contactSummary" name="editContactSummary"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control time" id="edit-nextContactTime" name="editNextContactTime">
								</div>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="edit-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="edit-address" name="editAddress"></textarea>
                                </div>
                            </div>
                        </div>


                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="update()">更新</button>
                        </div>

					</form>
					
				</div>

			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>线索列表</h3>
			</div>
		</div>
	</div>
	
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
	
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

			<%--<input type="hidden" id="hidden-fullname">
			<input type="hidden" id="hidden-job">
			<input type="hidden" id="hidden-phone">
			<input type="hidden" id="hidden-mphone">
			<input type="hidden" id="hidden-owner">
			<input type="hidden" id="hidden-state">
			<input type="hidden" id="hidden-source">--%>


			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;" action="clue/index.do" id="qf">

					<input type="hidden" id="pageNum" name="pageNum" value="${clueSplitAll.pageNum}">
					<input type="hidden" id="pageSize" name="pageSize" value="${clueSplitAll.pageSize}">

					<div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="fullname" name="fullname">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司</div>
				      <input class="form-control" type="text" id="job" name="job">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司座机</div>
				      <input class="form-control" type="text" id="phone" name="phone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索来源</div>
					  <select class="form-control" id="source" name="source">
					  	  <option></option>
						  <c:forEach items="${source}" var="a">
							  <option value="${a.value}">${a.text}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>
				  
				  <br>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="owner" name="owner">
				    </div>
				  </div>
				  
				  
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">手机</div>
				      <input class="form-control" type="text" id="mphone" name="mphone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索状态</div>
					  <select class="form-control" id="state" name="state">
					  	<option></option>
						  <c:forEach items="${clueState}" var="a">
							  <option value="${a.value}">${a.text}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>

				  <button type="submit" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 40px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createClueModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default"  onclick="updateBtn()"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" onclick="drop()"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
			</div>
			<div style="position: relative;top: 50px;">
				<table class="table table-hover" id="paging_table">

					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称</td>
							<td>公司</td>
							<td>公司座机</td>
							<td>手机</td>
							<td>线索来源</td>
							<td>所有者</td>
							<td>线索状态</td>
						</tr>
					</thead>
					<tbody id="body">

					<c:forEach items="${clueSplitAll.list}" var="c">
						<tr>
							<td><input type="checkbox" name="dx" value="${c.id}"/></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='clue/detail.do?id=${c.id}';">${c.fullname}${c.appellation}</a></td>
							<td>${c.job}</td>
							<td>${c.phone}</td>
							<td>${c.mphone}</td>
							<td>${c.company}</td>
							<td>${c.owner}</td>
							<td>${c.state}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>

				<br><br>

	<div>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li id="first">
					<a href="javascript:void(0);">
						<span aria-hidden="true">首页</span>
					</a>
				</li>
				<li id="prev">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">上一页</span>
					</a>
				</li>

				<c:forEach items="${clueSplitAll.navigatepageNums}" var="i">
					<li name="pageNum" <c:if test="${i == clueSplitAll.pageNum}">class="active" </c:if> ><a href="javascript:void(0);">${i}</a> </li>
				</c:forEach>

				<li id="next">
					<a href="javascript:void(0);" aria-label="Next">
						<span aria-hidden="true">下一页</span>
					</a>
				</li>

				<li id="last">
					<a href="javascript:void(0);">
						<span aria-hidden="true">末页</span>
					</a>
				</li>
				<span style="font-size: 20px;margin-left: 5px">
						共条 ${clueSplitAll.total} 记录，共${clueSplitAll.pages}页，每页行数
					<select id="setRows">
						<option value="5" <c:if test="${5 == clueSplitAll.pageSize}">selected</c:if> >5</option>
						<option value="10" <c:if test="${10 == clueSplitAll.pageSize}">selected</c:if> >10</option>
						<option value="20" <c:if test="${20 == clueSplitAll.pageSize}">selected</c:if> >20</option>
						<option value="30" <c:if test="${30 == clueSplitAll.pageSize}">selected</c:if> >30</option>
					</select>
				</span>
			</ul>
		</nav>
	</div>

	<script>

		//1:初始化变量
		var pageNum=${clueSplitAll.pageNum};//当前页码
		var pages=${clueSplitAll.pages};//总页数
		var hasPreviousPage=${clueSplitAll.hasPreviousPage};//还有上一页
		var hasNextPage =${clueSplitAll.hasNextPage};//还有下一页
		//2:判断按钮状态
		if (!hasPreviousPage){
			$("#prev").addClass("disabled");
			$("#first").addClass("disabled");
		}
		if (!hasNextPage){
			$("#next").addClass("disabled");
			$("#last").addClass("disabled");
		}
		//3：按钮事件监听
		$("#first").click(function (){
			if (!$("#first").hasClass("disabled")){
				$("#pageNum").val(1);
				$("#qf").submit();
			}
		})
		$("#prev").click(function (){
			if (!$("#prev").hasClass("disabled")){
				$("#pageNum").val(pageNum-1);
				$("#qf").submit();
			}
		})
		$("#next").click(function (){
			if (!$("#next").hasClass("disabled")){
				$("#pageNum").val(pageNum+1);
				$("#qf").submit();
			}
		})
		$("#last").click(function (){
			if (!$("#last").hasClass("disabled")){
				$("#pageNum").val(pages);
				$("#qf").submit();
			}
		})

		//4:每页行数改变事件监听
		$("#setRows").change(function (){
				$("#pageSize").val($(this).val());
				$("#pageNum").val(1);
				$("#qf").submit();

		})
		//5:点击页码事件监听
		$("li[name='pageNum']").click(function (){
			if(!$(this).hasClass("active")){
				$("#pageNum").val($(this).children("a").html());
				$("#qf").submit();
			}
		})

	</script>



		</div>
		
	</div>
</body>
</html>