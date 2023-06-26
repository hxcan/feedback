package com.stupidbeauty.feedback;

/**
  * Protobuf type {@code com.stupidbeauty.comgooglewidevinesoftwaredrmremover.FeedbackMessage}
  */
public  class FeedbackMessage 
{
  private String model_ = ""; //!< The model name of the phone.
  private String osversion_ = ""; //!< The os versxion.
  private String manufacturer_ = ""; //!< The manufacturer string.
  private String developerEmail=""; //!< The developer email.
  private String packageName=""; //!< package name.
  
  public String getPackageName()
  {
    return packageName;
  } // public String getPackageName()
  
  public void setPackageName(String packageName) // SEt the package name.
  {
    this.packageName=packageName;
  } // public void setPackageName(String packageName)
  
  public String getDeveloperEmail()
  {
    return developerEmail;
  } // public String getDeveloperEmail()
  
  public void setDeveloperEmail(String developerEmail) // Set the developer email.
  {
    this.developerEmail=developerEmail;
  } // public void setDeveloperEmail(String developerEmail)
  
  public FeedbackMessage() 
  {
    feedbacktext_ = "";

    emailAddress_ = "";
  }

  /**
    * <pre>
    *用户的邮件地址。
    * </pre>
    *
    * <code>optional string emailAddress = 4;</code>
    */
  public void setEmailAddress( java.lang.String value) 
  {
    if (value == null) 
    {
      throw new NullPointerException();
    }
    bitField0_ |= 0x00000008;
    emailAddress_ = value;
  }
  
  /**
    * <pre>
    *型号。
    * </pre>
    *
    * <code>optional string model = 12;</code>
    */
  public void setModel( java.lang.String value) 
  {
    if (value == null) 
    {
      throw new NullPointerException();
    }
    bitField0_ |= 0x00000800;
    model_ = value;
  }

  /**
    * <pre>
    *厂商名字。
    * </pre>
    *
    * <code>optional string manufacturer = 11;</code>
    */
  public void setManufacturer( java.lang.String value) 
  {
    if (value == null) 
    {
      throw new NullPointerException();
    }
    bitField0_ |= 0x00000400;
    manufacturer_ = value;
  }

  /**
    * <pre>
    *系统版本号。
    * </pre>
    *
    * <code>required string osversion = 1;</code>
    */
  public void setOsversion( java.lang.String value) 
  {
    if (value == null) 
    {
      throw new NullPointerException();
    }
    bitField0_ |= 0x00000001;
    osversion_ = value;
  }

  /**
  * <pre>
  *反馈文字内容。
  * </pre>
  *
  * <code>optional string feedbacktext = 1;</code>
  */
  public void setFeedbacktext( java.lang.String value) 
  {
    if (value == null) 
    {
      throw new NullPointerException();
    }
    bitField0_ |= 0x00000001;
    feedbacktext_ = value;
  }

  private int bitField0_;
  public static final int FEEDBACKTEXT_FIELD_NUMBER = 1;
  private String feedbacktext_; //!< the feedback text content.
  /**
    * <pre>
    *反馈文字内容。
    * </pre>
    *
    * <code>optional string feedbacktext = 1;</code>
    */
  public boolean hasFeedbacktext() 
  {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }

      /**
       * <pre>
       *型号。
       * </pre>
       *
       * <code>optional string model = 12;</code>
       */
      public java.lang.String getModel() 
      {
        return model_;
      }

      /**
       * <pre>
       *厂商名字。
       * </pre>
       *
       * <code>optional string manufacturer = 11;</code>
       */
      public java.lang.String getManufacturer() 
      {
        return manufacturer_;
      }

  /**
       * <pre>
       *系统版本号。
       * </pre>
       *
       * <code>required string osversion = 1;</code>
       */
      public java.lang.String getOsversion() 
      {
        return osversion_;
      }

  /**
    * <pre>
    *反馈文字内容。
    * </pre>
    *
    * <code>optional string feedbacktext = 1;</code>
  */
  public java.lang.String getFeedbacktext() 
  {
    return feedbacktext_;
  }

    public static final int LOGZIPFILE_FIELD_NUMBER = 2;

    /**
     * <pre>
     *压缩的日志文件。
     * </pre>
     *
     * <code>optional bytes logzipfile = 2;</code>
     */
    public boolean hasLogzipfile() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }

    public static final int DIAGNOSEINFORMATION_FIELD_NUMBER = 3;

    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 3;</code>
     */
    public boolean hasDiagnoseinformation() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }

    public static final int EMAILADDRESS_FIELD_NUMBER = 4;
    private String emailAddress_; //!< email address of the user.
    /**
     * <pre>
     *用户的邮件地址。
     * </pre>
     *
     * <code>optional string emailAddress = 4;</code>
     */
    public boolean hasEmailAddress() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *用户的邮件地址。
     * </pre>
     *
     * <code>optional string emailAddress = 4;</code>
     */
    public java.lang.String getEmailAddress() 
    {
      return emailAddress_;
    } // public java.lang.String getEmailAddress() 

    private byte memoizedIsInitialized = -1;

    private static final long serialVersionUID = 0L;
  }
