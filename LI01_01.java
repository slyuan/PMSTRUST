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
	
	
	
	//��Ŀ����
	private String Item_name;
	
	//�ʽ�����
	private String Intrust_flag1;
	
	//���첿��
	private String InputOrgID;
	
	//���о���
	private String InputUserID;
	
	//��������
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
	 * �����Ƿ�ͼ��ؼ�
	 */
	protected void setChartTag(){
	//	this.chartList.add(this.barChart);
	//	this.chartList.add(this.lineChart);
	// chartList.add(this.pieChart);
	}

	/**
	 * ���ø��Ͽؼ����磺radio,checkbox
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
				Title="�ֳ���鱨��";
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
