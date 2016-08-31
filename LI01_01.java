package com.amarsoft.app.natrust;

import java.io.Serializable;

import com.amarsoft.are.ARE;
import com.amarsoft.are.jbo.BizObject;
import com.amarsoft.are.jbo.BizObjectManager;
import com.amarsoft.are.jbo.BizObjectQuery;
import com.amarsoft.are.jbo.JBOFactory;
import com.amarsoft.biz.formatdoc.model.FormatDocData;
import com.amarsoft.dict.als.manage.NameManager;

public class LI01_01 extends FormatDocData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	//项目名称
	private String Item_name;
	
	//资金性质
	private String Intrust_flag1;
	
	//经办部门
	private String InputOrgID;
	
	//信托经理
	private String InputUserID;
	
	//调查日期
	private String InputDate;
	
	private String Title;
	
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public LI01_01() {

	}
	
	/**
	 * 设置是否图表控件
	 */
	protected void setChartTag(){
	//	this.chartList.add(this.barChart);
	//	this.chartList.add(this.lineChart);
	// chartList.add(this.pieChart);
	}

	/**
	 * 设置复合控件：如：radio,checkbox
	 */
	public void setMultiInputs(){
		ARE.getLog().trace(this.getClass().getName()+".setUpdateFields()");
		this.setMultiInputs("opinion1MultiSelect,opinion2SingleSelect,extobj0.attr1", true);
		super.setMultiInputs();
	}

	

	public boolean initObjectForRead() {

		String sObjectNo=this.getRecordObjectNo();
		System.out.println("sObjectNo--->"+sObjectNo);
		if(sObjectNo==null) sObjectNo="";
		BizObjectManager m=null;
		BizObjectManager mm=null;
		BizObjectManager mmm=null;
		BizObjectQuery q=null;
		BizObjectQuery qq=null;
		BizObjectQuery qqq=null;
		BizObject bo=null;
		BizObject bb=null;
		BizObject bbb=null;
		String contractserialno;
		String relativeserialno;
		String ObjectType;

		
		try {
			m = JBOFactory.getFactory().getManager("jbo.natrust.APPLY_REPORT_RECORD");
			q = m.createQuery("SERIALNO=:SERIALNO").setParameter("SERIALNO",sObjectNo);	
			bo=q.getSingleResult();
			if(bo != null){
				Title="现场检查报告";
				contractserialno = bo.getAttribute("ContractSerialNo").toString();
				mm = JBOFactory.getFactory().getManager("jbo.app.BUSINESS_CONTRACT");
				qq = mm.createQuery("SERIALNO=:SERIALNO").setParameter("SERIALNO",contractserialno);
				bb = qq.getSingleResult();
				if(bb != null){
					relativeserialno = bb.getAttribute("RelativeSerialNo").toString();
					
					mmm = JBOFactory.getFactory().getManager("jbo.app.BUSINESS_APPLY");
					qqq = mmm.createQuery("SERIALNO=:SERIALNO").setParameter("SERIALNO",relativeserialno);
					bbb = qqq.getSingleResult();
					
					if(bbb != null){
						Item_name = bbb.getAttribute("E_Item_name").toString();
						Intrust_flag1 = bbb.getAttribute("E_Intrust_flag1").toString();
						InputOrgID = NameManager.getOrgName(bbb.getAttribute("InputOrgID").toString()) ;
						InputUserID = NameManager.getUserName(bbb.getAttribute("InputUserID").toString()) ;
						InputDate = bbb.getAttribute("InputDate").toString();
						
					}
					
				}
			}	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}


	public boolean initObjectForEdit() {

		return true;
	}

	public String getItem_name() {
		return Item_name;
	}

	public void setItem_name(String item_name) {
		Item_name = item_name;
	}

	public String getIntrust_flag1() {
		return Intrust_flag1;
	}

	public void setIntrust_flag1(String intrust_flag1) {
		Intrust_flag1 = intrust_flag1;
	}

	public String getInputOrgID() {
		return InputOrgID;
	}

	public void setInputOrgID(String inputOrgID) {
		InputOrgID = inputOrgID;
	}

	public String getInputUserID() {
		return InputUserID;
	}

	public void setInputUserID(String inputUserID) {
		InputUserID = inputUserID;
	}

	public String getInputDate() {
		return InputDate;
	}

	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}

}
