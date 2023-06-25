package com.stupidbeauty.sbrowserandroid.activity;

import java.util.HashMap;

import android.app.Activity;
import android.view.KeyEvent;

public class SecrectKeyCodePolicyManager 
{

  public enum KeyAction {
	  Pending, //!<等待后续按键。
	  NextStep, //!<下一步。
 ShowHiddenForm, //!<显示隐藏表单。
 ForceEnableFunction //!<强制启用某个在当前条件下不会启用的功能。
	}



	/**
	*密码代码解释器。
	*/
private static HashMap<Activity,String> contextKeySequenceStringMap=new HashMap<Activity,String>(); //上下文与按键序列字符串之间的映射。

	/**
	*向指定活动的列表里追加按键代码，并且返回解析结果。
	*/
	public static SecretCodeParseResult appendKeyCode(Activity simoStartupActivity, int keyCode) 
	{
	SecretCodeParseResult result=new SecretCodeParseResult(); //结果。
	
	KeyAction resultAction=KeyAction.Pending; //结果动作。
	String currentKeySequenceString=contextKeySequenceStringMap.get(simoStartupActivity); //获取对应的按键序列字符串。
	
	boolean consumeEvent=false; //是否应当消化掉事件。
	
	if (currentKeySequenceString==null) //不存在对应的序列字符串。
	{
	  currentKeySequenceString=""; //创建空白字符串。
	} //if (currentKeySequenceString==null) //不存在对应的序列字符串。
	
		
		if (keyCode==KeyEvent.KEYCODE_VOLUME_UP) //音量加。
		{
			currentKeySequenceString=currentKeySequenceString+'u'; //追加一个u,表示音量增加。

			if (currentKeySequenceString.endsWith("dduu")) //最后按的是下下上上。下一步。
			{
				resultAction=KeyAction.NextStep; //结果是，下一步。
				
				currentKeySequenceString=""; //字符串清空。
			} //else if (keySequenceString.endsWith("dduu")) //最后按的是下下上上。
			else if (currentKeySequenceString.endsWith("dudu")) //最后按的是下上下上。显示隐藏表单。
			{
				resultAction=KeyAction.ShowHiddenForm; //结果是，显示隐藏表单。
				
				currentKeySequenceString=""; //字符串清空。
			} //else if (keySequenceString.endsWith("dduu")) //最后按的是下下上上。
			
			contextKeySequenceStringMap.put(simoStartupActivity,currentKeySequenceString); //将关系加入到映射中。
			
			consumeEvent=true; //阻断此事件。
		} //else if ((keyCode==KeyEvent.KEYCODE_VOLUME_UP) || (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)) //音量键。
		else if (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN) //音量减。
		{
			currentKeySequenceString=currentKeySequenceString+'d'; //追加一个d,表示音量减小。
			
			if (currentKeySequenceString.endsWith("duud")) //最后按的是下上上下。强制启用当前条件下不应当启用的功能。
			{
				resultAction=KeyAction.ForceEnableFunction; //结果是，强制启用功能。
				
				currentKeySequenceString=""; //字符串清空。
			} //else if (keySequenceString.endsWith("dduu")) //最后按的是下下上上。
			
			contextKeySequenceStringMap.put(simoStartupActivity,currentKeySequenceString); //将关系加入到映射中。
			
			consumeEvent=true; //阻断此事件。
		} //else if ((keyCode==KeyEvent.KEYCODE_VOLUME_UP) || (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)) //音量键。
		else  //其它按钮。
		{
			resultAction=KeyAction.Pending; //结果是，仍在等待后续按键输入。
		} //else //其它按钮。
		
		result.setAction(resultAction); //设置动作。
		result.setConsumeEvent(consumeEvent); //设置是否要消化掉事件。
		
		return result;
		} //public static KeyAction appendKeyCode(Activity simoStartupActivity, int keyCode,Boolean consumeEvent) 

} //public class SecrectKeyCodePolicyManager 
