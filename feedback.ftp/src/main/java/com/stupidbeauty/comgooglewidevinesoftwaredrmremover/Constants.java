package com.stupidbeauty.comgooglewidevinesoftwaredrmremover;



/**
 * 各个常量。
 * @author root
 *
 */
public class Constants 
{
	/**
	 * 各个网址。
	 */
	public static class Url
	{
		public static String OptimizeRepairProGooglePlayUrl="https://play.google.com/store/apps/details?id=com.stupidbeauty.comgooglewidevinesoftwaredrmremover.pro"; //!<优化修复专业版应用程序的google play地址。
	} //public static class Url

	/**
	 * 与MQTT的信息相关的常量。
	 * @author root 蔡火胜。
	 *
	 */
	public static class MqttInfo
	{
		public static final String MQTTURL="tcp://120.76.44.249"; //!<MQTT服务器地址。
		public static final String topicPrefix="/lab/director/"; //!<主题前缀。
	} //public static class MqttInfo

	/**
	 * 文件路径。
	 * @author root 蔡火胜。
	 *
	 */
	public static class FilePath
	{

		public static final String OsiLogPath = "/skyroam/cfg/gmates/osi_log.txt"; //!<OSI日志文件路径。
		public static final String UnknownDeviceMacAddr = "UnkownMacAddr"; //!<未知的网卡物理地址。

	} //public static class FilePath



	/**
	 * 原始消息。
	 * @author root
	 *
	 */
	public final class NativeMessage 
	{
		public static final String NOTIFY_READ_GMATE_LOG_SN = "notifyReadGmateLogSn";
		public static final String NOTIFY_READ_GMATE_LOG = "notifyReadGmateLog"; //!<要求读取Gmate日志。
		public static final String NOTIFY_APN_RESPONSE_STATUS = "notifyApnResponseStatus"; //!<设置APN的结果。
		public static final String NOTIFY_SIGNAL = "notifySignal";
		public static final String NOTIFY_WIFI_CONN_NUM = "notifyWifiConnNum";
		public static final String STATUS_TEXT_CHANGE = "com.skyroam.blue.constants.nativemessage.statusTextChange"; //!<Status text change.
		                public static final String SHARE_TEXT= "com.skyroam.blue.constants.nativemessage.shareText"; //!<分享文字内容。
		public static final String STATUS_TEXT_KEY = "com.skyroam.blue.constants.nativemessage.statusTextKey"; //!<The status text key.
                                public static final String SHARE_TEXT_KEY = "com.skyroam.blue.constants.nativemessage.shareTextKey"; //!<The status text key.

		public static final String HIDE_CHECK_ROOT_ACCESS_GROUP_BOX="com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.hideCheckRootAccessGroupBox"; //!<隐藏检测ROOT权限分组框 。

		public static final String SET_LABEL_TEXT_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.setLabelTextKey"; //!<设置标签文字的键。
		public static final String SET_LABEL_TEXT_QT_NAME_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.setLabelTextQtNameKey"; //!<要文字的标签部件的QT名字的键。

		public static final String SET_WIDGET_VISIBLE_QT_NAME_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.setWidgetVisibleQtNameKey"; //!<设置部件可见性的QT名字.
		public static final String SET_WIDGET_VISIBLE_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.setWidgetVisibleKey"; //设置部件可见性的可见性键。
		public static final String SHOW_REPORT_FINISHED_DIALOG = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.showReportFinishedDialog"; //!<显示修复完毕的对话框。

		public static final String SET_WIDGET_ENABLED_QT_NAME_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.setWidgetEnabledQtNameKey"; //!<设置部件可用性的QT名字
		public static final String SET_WIDGET_ENABLED_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.setWidgetEnabledKey"; //!<设置部件可用性的可用性键。




		public static final String ANDROID_SEND_HELP_TRANSLATE_EMAIL= "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.androidSendHelpTranslateEmail"; //!<使用安卓接口来发送请求翻译的邮件。


		public static final String HELP_TRANSLATE_EMAIL_ADDRESS_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.helpTranslateEmailAddressKey"; //!<请求翻译邮件地址键。
		

		public static final String HELP_TRANSLATE_EMAIL_SUBJECT_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.helpTranslateEmailSubjectKey"; //!<被挤掉，要求退出。
		
		public static final String HELP_TRANSLATE_EMAIL_BODY_KEY = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.constants.nativemessage.helpTranslateEmailBodyKey"; //!<We failed to login by admin.

		



		
		
		/** 充电状态 */
		public static final String NOTIFY_CHARGGING_STATE = "NOTIFY_CHARGGING_STEP"; //!<电池充电状态发生变化。
		
		/** SIM卡登录的运营商 */
		public static final String NOTIFY_SIM_OPERATOR = "notifySimOperator";
		public static final String NOTIFY_SIM_OPERATOR_STRING_OPERATOR = "operator";

		/** 报告SIM卡里联系人数量 */
		public static final String NOTIFY_SIM_CONTACTS_NUMBER = "notifySimContactsNumber";
		public static final String NOTIFY_SIM_CONTACTS_NUMBER_INT_NUMBER = "number";

		/** 报告一个SIM中联系人的信息 */
		public static final String NOTIFY_SIM_CONTACT = "notifySimContact";
		public static final String NOTIFY_SIM_CONTACT_STRING_NAME = "name";
		public static final String NOTIFY_SIM_CONTACT_STRING_NUMBER = "number";

		/** 报告SIM卡PIN码验证的结果 */
		public static final String NOTIFY_PIN_VERIFICATE = "notifyPINVerificate";
		public static final String NOTIFY_PIN_VERIFICATE_SHORT_TIMES = "times";

		/** 报告SIM卡PUK码验证的结果 */
		public static final String NOTIFY_PUK_VERIFICATE = "notifyPUKVerificate";
		public static final String NOTIFY_PUK_VERIFICATE_SHORT_TIMES = "times";

		/*********************************************** update *************************************************/

		public static final String NOTIFY_FIRMWARE_REPORT_VERSION = "notifyFirmwareReportVersion";

		public static final String NOTIFY_FIRMWARE_START_UPDATE_ACK = "notifyFirmwareStartUpdateAck";

		public static final String NOTIFY_FIRMWARE_UPDATE_DATA_ACK = "notifyFirmwareUpdateDataAck";
		public static final String NOTIFY_FIRMWARE_UPDATE_DATA_ACK_LONG_SEQUENCE = "sequence";

		public static final String NOTIFY_FIRMWARE_COMPLETE_UPDATE = "notifyFirmwareCompleteUpdate";
		public static final String NOTIFY_FIRMWARE_COMPLETE_UPDATE_SHORT_STATE = "state";

		public static final String NOTIFY_FIRMWARE_UPDATE_EXCEPTION = "notifyFirmwareUpdateException";

		public static final String NOTIFY_FIRMWARE_REBOOT = "notifyFirmwareReboot";

		/*********************************************** Network *************************************************/

		/*********************************************** Call *************************************************/

		/** 正在拨号 */
		public static final String NOTIFY_CALL_DAILLING = "notifyCallDailling";
		public static final String NOTIFY_CALL_DAILLING_SHORT_CALLID = "callId";

		/** 电话拨打失败 */
		public static final String NOTIFY_CALL_DIALING_ERROR = "notifyCallDialingError";
		public static final String NOTIFY_CALL_DIALING_ERROR_SHORT_CALLID = "callId";
		public static final String NOTIFY_CALL_DIALING_ERROR_SHORT_ERRCODE = "errCode";

		/** 来电 */
		public static final String NOTIFY_CALL_RING = "notifyCallRinging";
		public static final String NOTIFY_CALL_RING_SHORT_CALLID = "callId";
		public static final String NOTIFY_CALL_RING_STRING_NUMBER = "number";

		/** 电话已连接 */
		public static final String NOTIFY_CALL_CONNECTED = "notifyCallConnected";
		public static final String NOTIFY_CALL_CONNECTED_SHORT_CALLID = "callId";

		/** 电话已保持 */
		public static final String NOTIFY_CALL_HOLD = "notifyCallHold";
		public static final String NOTIFY_CALL_HOLD_SHORT_CALLID = "callId";

		/** 电话已恢复 */
		public static final String NOTIFY_CALL_RESUME = "notifyCallResume";
		public static final String NOTIFY_CALL_RESUME_SHORT_CALLID = "callId";

		/** 语音开始 */
		public static final String NOTIFY_CALL_AUDIO_START = "notifyCallAudioStart";

		public static final String NOTIFY_CALL_TIMER = "notifyCallTimer";
		public static final String NOTIFY_CALL_TIMER_SHORT_CALLID = "callId";
		public static final String NOTIFY_CALL_TIMER_LONG_DURATION = "duration";
		public static final String NOTIFY_CALL_TIMER_STRING_DURATION = "durationString";
		/** 电话已挂断 */
		public static final String NOTIFY_CALL_HANDUP = "notifyCallHandup";
		public static final String NOTIFY_CALL_HANDUP_SHORT_CALLID = "callId";

		/** 语音结束 */
		public static final String NOTIFY_CALL_AUDIO_END = "notifyCallAudioEnd";

		/** 接收USSD */
		public static final String NOTIFY_RECEIVE_USSD = "notifyReceiveUSSD";

		/*********************************************** Call log *************************************************/

		public static final String NOTIFY_CALL_LOG = "notifyCallLog";
		public static final String NOTIFY_CONTACT_LIST_CHANGED = "notifyContactSyncFinished";
		/** 呼出通话记录 */
		public static final String NOTIFY_OUTGOING_CALLLOG = "notifyOutgoingCallLog";
		public static final String NOTIFY_OUTGOING_CALLLOG_SHORT_CALLID = "callId";
		public static final String NOTIFY_OUTGOING_CALLLOG_STRING_NUMBER = "number";
		public static final String NOTIFY_OUTGOING_CALLLOG_LONG_DATE = "date";
		public static final String NOTIFY_OUTGOING_CALLLOG_INT_DURATION = "duration";

		/** 呼入通话记录 */
		public static final String NOTIFY_INCOMING_CALLLOG = "notifyIncomingCallLog";
		public static final String NOTIFY_INCOMING_CALLLOG_SHORT_CALLID = "callId";
		public static final String NOTIFY_INCOMING_CALLLOG_STRING_NUMBER = "number";
		public static final String NOTIFY_INCOMING_CALLLOG_LONG_DATE = "date";
		public static final String NOTIFY_INCOMING_CALLLOG_INT_DURATION = "duration";

		/** 更新通话记录 */
		public static final String NOTIFY_UPDATE_CALLLOG = "notifyUpdateCallLog";
		public static final String NOTIFY_UPDATE_CALLLOG_INT_ID = "id";
		public static final String NOTIFY_UPDATE_CALLLOG_SHORT_STATE = "state";
		public static final String NOTIFY_UPDATE_CALLLOG_STRING_BUMBER = "number";
		public static final String NOTIFY_UPDATE_CALLLOG_INT_DURATION = "duration";
		public static final String NOTIFY_UPDATE_CALLLOG_BOOLEAN_INCOMING = "incoming";

		/*********************************************** SMS *************************************************/

		/** 添加新短信 */
		public static final String NOTIFY_ADD_INBOX_SMS = "notifyAddInboxSms";
		public static final String NOTIFY_ADD_INBOX_SMS_LONG_DATE = "date";
		public static final String NOTIFY_ADD_INBOX_SMS_STRING_BUMBER = "number";
		public static final String NOTIFY_ADD_INBOX_SMS_STRING_MESSAGE = "message";

		/** 短信发送失败 */
		public static final String NOTIFY_SEND_FAILED_SMS = "notifySendFailedSms";
		public static final String NOTIFY_SEND_FAILED_SMS_STRING_BUMBER = "number";
		public static final String NOTIFY_SEND_FAILED_SMS_SHORT_ERROR = "error";

		public static final String NOTIFY_CALL_MUTE = "notifyCallMute";
		public static final String NOTIFY_CALL_MUTE_BOOLEAN_MUTE = "mute";

		public static final String NOTIFY_CALL_SPEAKER = "notifyCallSpeaker";
		public static final String NOTIFY_CALL_SPEAKER_BOOLEAN_SPEAKER = "speaker";
		public static final String NOTIFY_LOGIN_RESULT = "notifyLoginResult"; //!<登录结果。
		public static final String NOTIFY_LOGIN_RESULT_FAIL_REASON = "notifyLoginResultFailReason"; //!<登录失败原因。
	} //public final class NativeMessage 

	/**
	 * 常用常量。
	 * @author root 蔡火胜。
	 *
	 */
	public final class Common 
	{
		/** 控制业务tabs 的显示 */
		public static final String NATIVE_ACTION_SHOW_BUSINESS_TABS = "native_action.SHOW_TABS";
		public static final String NATIVE_ACTION_GONE_BUSINESS_TABS = "native_action.GONE_TABS";

		/** 服务已启动 */
		public static final String NATIVE_ACTION_SERVICE_FINISH = "native_action.SERVICE_FINISH"; //!<后台服务已启动。

		/** 显示下一个界面 */
		public static final String NATIVE_ACTION_ON_SHOW_NEXT_VIEW = "native_action.ON_SHOW_NEXT_VIEW"; //!<显示下一个界面。
		public static final String NATIVE_ACTION_ON_SHOW_NEXT_VIEW_STRING_ACTIVITY = "activity";

		/** 显示前一个界面 */
		public static final String NATIVE_ACTION_ON_SHOW_PREVIOUS_VIEW = "native_action.ON_SHOW_PREVIOUS_VIEW";
		public static final String NATIVE_ACTION_ON_SHOW_PREVIOUS_VIEW_STRING_KEY = "key";

		/** 显示PIN输入框 */
		public static final String NATIVE_ACTION_ON_SHOW_PIN_OR_PUK_VIEW = "native_action.ON_SHOW_PIN_OR_PUK_VIEW";
		public static final String NATIVE_ACTION_ON_SHOW_PIN_OR_PUK_VIEW_STRING_JSON = "json";

		/** 隐藏PIN输入框 */
		public static final String NATIVE_ACTION_ON_HIDE_PIN_OR_PUK_VIEW = "native_action.ON_HIDE_PIN_OR_PUK_VIEW";
		/** 切换tab */
		public static final String NATIVE_ACTION_ON_SWITCH_TAB_VIEW = "native_action.ON_SWITCH_TAB_VIEW";
		public static final String NATIVE_ACTION_ON_SWITCH_TAB_VIEW_INT_INDEX = "index";

		/** 播放铃音 */
		public static final String NATIVE_ACTION_RING = "native_action.RING"; //!<播放铃声。
		public static final String NATIVE_ACTION_RING_STRING_JSON = "json";

		/** 更新logo状态 */
		public static final String GMATE_UPDATE_LOGO_STATE = "gmate.UPDATE_LOGO_STATE";

		/** 字符串常量，存放在Intent中的设备对象 */
		public static final String DEVICE = "DEVICE";
		/** Action：设备列表 */
		public static final String ACTION_FOUND_DEVICE = "ACTION_FOUND_DEVICE";
		/** Action：关闭后台Service */
		public static final String ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE";
		/** code of wifi */
		public static final int WIFI_DEVICE_CODE = 1;
		/** code of bluetooth */
		public static final int BLUETOOTH_DEVICE_CODE = 0;

		public static final String NATIVE_SETTING_PREFERENCES_NAME = "native_setting_preferences_name";
		public static final String IS_FAQ_KEYWORK_SEARCHING = "is_faq_keywork_searching";
		public static final String IS_FAQ_INIT_LOCAL_DATA = "is_faq_init_local_data";
		public static final String IS_SERVICE_HAS_BEAN_START = "is_service_has_bean_start"; //!<后台服务是否已经启动。

		public static final String PREF_TEMP_SN = "PREF_TEMP_SN";
		public static final String PREF_TEMP_VERSION = "PREF_TEMP_VERSION";
		public static final String HAS_BEAN_LOGIN_SUCCESS = "has_bean_login_success"; //!<是否曾经成功登录过。
		public static final String HAS_BEAN_CLICK_LOGING_BTN = "has_bean_click_loging_btn";

		public static final String PREF_LOGIN_USER_NAME = "PREF_LOGIN_USER_NAME";
		public static final String PREF_LOGIN_USER_PWD = "PREF_LOGIN_USER_PWD";
		public static final String MATCH_ACCOUNT_WITH_GMATE_KEY = "match_account_with_gmate"; //!<当前登录的skyroam id是否与设备绑定的ID匹配。
		
		
		/**
		 * 静态网页类型。
		 */
		public static final String STATIC_WEBPAGE_COVERAGE = "staticWebPageTerritory"; //!<覆盖地区。
		public static final String STATIC_WEBPAGE_TYPE = "staticWebPageType"; //!<参数，静态网页类型。
		public static final String STATIC_WEBPAGE_PRODUCTS = "staticWebPageProducts"; //!<产品介绍。
		public static final String STATIC_WEBPAGE_INSTRUCTIONS = "staticWebPageInstructions"; //!<使用说明。
		
		public static final String STATIC_WEBPAGE_SN_PARAM_KEY = "sn"; //!<sn参数的键。	
		public static final String STATIC_WEBPAGE_MODULE_PARAM_KEY = "module"; //!<module参数键。
		
		public static final String STATIC_WEBPAGE_MODULE_COVERAGE = "coverage"; //!<module参数值，覆盖地区。
		public static final String STATIC_WEBPAGE_MODULE_PRODUCTS = "products"; //!<module参数值，产品介绍。
		public static final String STATIC_WEBPAGE_MODULE_INSTRUCTIONS = "userguide"; //!<module参数值，使用说明。
	

		public static final String STATIC_WEBPAGE_CSYSTEM_PARAM_KEY = "csystem"; //!<csystem 参数的键。
		public static final String STATIC_WEBPAGE_CSYSTEM_ANDROID = "2"; //!<csystem参数的值，android.
		public static final String STATIC_WEBPAGE_LANG_PARAM_KEY = "lang"; //!<lang参数的值。
		public static final String STATIC_WEBPAGE_SKYROAMID_PARAM_KEY = "skyroam_id"; //!<skyroam id 参数的键。
		public static final String MATCH_ACCOUNT_NAMES_JSON = "match_account_names_json"; //!<以JSON字符串表示的，匹配的账户名集合。
		public static final String LOGIN_ACTIVITY_HEADING_FROM_KEY = "loginActivityHeadingFrom"; //!<Where are we heading from to the LoginActivity.
	} //public final class Common 

	public final class SearchDevices {
		public static final String ACTION_UPDATE_DEVICE_LIST = "ACTION_UPDATE_DEVICE_LIST";
		public static final String ACTION_SHOW_SEARCH_PROGRESS = "ACTION_SHOW_SEARCH_PROGRESS";
		public static final String ACTION_GONE_SEARCH_PROGRESS = "ACTION_GONE_SEARCH_PROGRESS";
		public static final String DEVICE_NAME = "DEVICE_NAME";
		public static final String DEVICE_ADDR = "DEVICE_ADDR";
		public static final String DEVICE_CODE = "DEVICE_CODE";
		public static final String DEVICE_SIGNAL_LEVEL = "DEVICE_SIGNAL_LEVEL";
	}

	/**
	 * 连接状态定义。
	 * @author root Hxcan. The socket connection status to gmate.
	 *
	 */
	public final class Connection 
	{
		public static final int CONNECT_STATE_DISCONNECTED = 0;
		public static final int CONNECT_STATE_CONNECTTING = 1; //!<正在连接。
		public static final int CONNECT_STATE_FAIL = 2;
		public static final int CONNECT_STATE_CONNECTED = 3; //!<已经建立连接。
		public static final int CONNECT_STATUS_DISCONNECT = 4; //!<已断开连接。
		public static final int CONNECT_STATUS_IS_MASTER = 5; //!<我们是管理员。
		public final static int CONNECTION_STATE_IS_LOGINED = 6; //!<Logged in normally.
		public final static int CONNECTION_STATE_SERVER_REFUSE = 7; //!<Server refuse connection.
		public final static int CONNECTION_STATE_UNDEFINED=8; //!<Undefine connection status.The default.
	} //public final class Connection

	public final class GmateVirtualSimType 
	{
		public static final int GMATE_VIRTUAL_SIM_NULL = 0;
		public static final int GMATE_VIRTUAL_SIM_APPLYING = 1;
		public static final int GMATE_VIRTUAL_SIM_DATA_ONLY = 2;
		public static final int GMATE_VIRTUAL_SIM_VOICE_ONLY = 3;
		public static final int GMATE_VIRTUAL_SIM_SMS_ONLY = 4;
		public static final int GMATE_VIRTUAL_SIM_DATA_AND_VOICE = 5;
		public static final int GMATE_VIRTUAL_SIM_DATA_AND_SMS = 6;
		public static final int GMATE_VIRTUAL_SIM_VOICE_AND_SMS = 7;
		public static final int GMATE_VIRTUAL_SIM_DATA_VOICE_SMS = 8;
		public static final int GMATE_VIRTUAL_SIM_MAX = 9;

	}

	/**
	 * 移动网络类型。
	 * @author root 蔡火胜。
	 *
	 */
	public final class MobileNetType 
	{

		public static final int SIMO_NETWORK_TYPE_0 = 0; //!<默认网络类型。
		public static final int SIMO_NETWORK_TYPE_2G = 2; //!<2G网络。
		public static final int SIMO_NETWORK_TYPE_3G = 3; //!<3G网络。
	} //public final class MobileNetType

	public final class SimoStackVersionModle 
	{
		public static final int SIMO_STACK_VERSION_MODLE_AUDIO = 0;
		public static final int SIMO_STACK_VERSION_MODLE_MAX = 1;
	} //public final class SimoStackVersionModle

	/**
	 * The constants about activity.
	 * @author root Hxcan.
	 *
	 */
	public final class Activity 
	{
		/**
		 * The constants about LoginActivity.
		 * @author root Hxcan.
		 *
		 */
		public final class LoginActivity
		{
			/**
			 * The constants about where are we heading from to the LoginActivity.
			 * @author root Hxcan.
			 *
			 */
			public final class HeadingFrom
			{
				public static final int SettingActivity = 1; //!<We are heading from SettingActivity,that is to say , we are "Logout".
				public static final int OtherSituation = 2; //!<We are heading from other situation.
			} //public final class HeadingFrom
		} //public final class LoginActivity
	} //public final class AudioStreamVersion

	public final class FAQTag 
	{
		public static final String TAG_STRING = "string";
		public static final String TAG_QUESTION = "question";
		public static final String TAG_ANSWER = "answer";
		public static final String TAG_CAT = "cat";
		public static final String TAG_KEYWORDS = "keywords";

		public static final String TAG_FAQ_VERSION = "version";
		public static final String TAG_FAQ_URL = "url";
	} //public final class FAQTag

	public final class LangId {
		public static final int LANG_EN = 1;
		public static final int LANG_ZH_CN = 2;
		public static final int LANG_ZH_TW = 3;
	}

	/**
	 * 对应于常见问题的语言键。
	 * @author root 蔡火胜。
	 *
	 */
	public final class FAQLangKey 
	{
		public static final String LANG_KEY_EN = "en"; //英文。
		public static final String LANG_KEY_ZH_CN = "cn_zh"; //简体中文。
		public static final String LANG_KEY_ZH_TW = "cn_tw"; //繁体中文。
	} //public final class FAQLangKey

	public final class SimConfigTag 
	{
		public static final String TAG_SIM = "sim";
		public static final String TAG_TYPE = "type";
		public static final String TAG_TAB = "tab";
		public static final String TAG_ITEM = "item";
		public static final String TAG_TPL = "tpl";
		public static final String TAG_DATA = "data";
	}

	public final class SimConfigKey {
		// data source key
		public static final String V_SIM_KEY = "vsim";
		public static final String H_SIM_KEY = "hsim";

		public static final String TAB_ACCOUNT_KEY = "account";
		public static final String TAB_VOICE_KEY = "voice";
		public static final String TAB_SMS_KEY = "sms";

		public static final String ACCOUNT_INFO_KEY = "info";
		public static final String ACCOUNT_CHARGE_KEY = "charge";
		public static final String ACCOUNT_BILLING_KEY = "billing";
		public static final String ACCOUNT_BALANCE_KEY = "balance";
		public static final String ACCOUNT_AERA_KEY = "aera";
	}

	/**
	 * Account info type to send to gmate.
	 * @author root Hxcan.
	 *
	 */
	public final class AccountRequestType 
	{
		public static final int REQUEST_BY_ADMIN = 1; //!<Login by admin .
		public static final int REQUEST_BY_SKYROAM_ID = 2; //!<Send skyroam id login name.
	} //public final class AccountRequestType

	/**
	 * 账户的有效性。
	 * @author root 蔡火胜。
	 *
	 */
	public final class Networks
	{
		public static final String RabbitMQUserName="optimizerepair"; //!<RabbitMQ用户名。
		public static final String RabbitMQPassword="som3150"; //!<RabbitMQ密码。
		public static final String TRANSLATE_REQUEST_QUEUE_NAME = "com.stupidbeauty.comgooglewidevinesoftwaredrmremover.TranslateRequestQueue";

		public static final String TEST_QUEUE_NAME="com.stupidbeauty.comgooglewidevinesoftwaredrmremover.TestQueue"; //!<测试用的队列名字。

	} //public final class Networks
	
	/**
	 * 登录时的错误码。
	 * @author root 蔡火胜。
	 *
	 */
	public final class LoginErrorCode
	{
		/*0成功  10000  密码错误  10001  用户已经注销  10002无权登录   10003 无效账户  10004 帐户冻结*/
		public static final int LOGIN_ERROR_CODE_0 = 0;
		public static final int LOGIN_ERROR_CODE_1 = 10000;
		public static final int LOGIN_ERROR_CODE_2 = 10001; //!<用户已经注销。
		public static final int LOGIN_ERROR_CODE_3 = 10002; //!<无权登录。
		public static final int LOGIN_ERROR_CODE_4 = 10003; //!<账户无效。
		public static final int LOGIN_ERROR_CODE_5 = 10004; //!<账户已经被冻结。
	} //public final class LoginErrorCode

	/**
	 * 跟配置信息相关的常量。
	 * @author root 蔡火胜。
	 *
	 */
	public static final class Config
	{
		public static final String LOCAL_CONFIG_FILE_NAME = "localConfigFile.json"; //!<下载到本地的配置文件名。
	} //public static final class Config

	/**
	 * 被废弃掉的常量值。在某些清除动作中还需要用到它们。
	 * @author root 蔡火胜。
	 *
	 */
	public static final class Deprecated 
	{
		public static final String ADMIN_LOGING_STATUS_KEY = "admin_loging_status_key"; //!<管理员登录状态。
		public static final String MATCH_ACCOUNT_NAMES = "match_account_names"; //!<匹配的账户名集合。
	} //public static final String Deprecated

	/**
	 * 与OTA相关的常量。
	 * @author root 蔡火胜。
	 *
	 */
	public static class Ota
	{
		public static final int FILE_MOVE_REPLY_SUCCESS = 0x00; //!<成功。
	} //public static class Ota

	/**
	 * 与计时相关的一些值。
	 * @author root 蔡火胜。
	 *
	 */
	public static class Timing
	{
		public static final long SOCKET_EXCEPTION_SO_QUICKLY_MILLISECONDS = 4000; //!<如果在这么快的时间内，套接字刚开始建立连接就发生了异常，则认为是“太快发生异常了”。
	} //public static class Timing
}
