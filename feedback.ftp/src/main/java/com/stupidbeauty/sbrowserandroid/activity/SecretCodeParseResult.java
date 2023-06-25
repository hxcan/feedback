package com.stupidbeauty.sbrowserandroid.activity;

import com.stupidbeauty.sbrowserandroid.activity.SecrectKeyCodePolicyManager.KeyAction;

public class SecretCodeParseResult {

	private KeyAction action=KeyAction.Pending; //!<解析出来的动作。
	private Boolean consumeEvent=false; //!<是否要消化掉事件。
	
	/**
	*是否应当消化事件。
	*/
	     public Boolean getConsumeEvent()
	     {
	      return consumeEvent;
	     } //public Boolean getConsumeEvent()

	public void setAction(KeyAction resultAction) {
		
		action=resultAction; //记录。
		
	}

	/**
	*设置是否要消化掉事件。
	*/
	public void setConsumeEvent(Boolean consumeEvent) 
	{
	  this.consumeEvent=consumeEvent; //记录。
	

	  return;
		
	} //public void setConsumeEvent(Boolean consumeEvent) 

	public KeyAction getAction() {


		return action;
	}

}
