package com.stupidbeauty.feedback;

/**
  * Protobuf type {@code com.stupidbeauty.comgooglewidevinesoftwaredrmremover.FeedbackMessage}
  */
public  class FeedbackMessage 
{
  private java.lang.Object model_ = "";
  private java.lang.Object osversion_ = "";
  private java.lang.Object manufacturer_ = "";
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
    logzipfile_ = com.google.protobuf.ByteString.EMPTY;
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
  private volatile java.lang.Object feedbacktext_;
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
      public java.lang.String getModel() {
        java.lang.Object ref = model_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            model_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }

      /**
       * <pre>
       *厂商名字。
       * </pre>
       *
       * <code>optional string manufacturer = 11;</code>
       */
      public java.lang.String getManufacturer() {
        java.lang.Object ref = manufacturer_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            manufacturer_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }

  /**
       * <pre>
       *系统版本号。
       * </pre>
       *
       * <code>required string osversion = 1;</code>
       */
      public java.lang.String getOsversion() {
        java.lang.Object ref = osversion_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            osversion_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
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
    java.lang.Object ref = feedbacktext_;
    if (ref instanceof java.lang.String) 
    {
      return (java.lang.String) ref;
    }
    else 
    {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) 
      {
        feedbacktext_ = s;
      }
      return s;
    }
  }
  /**
    * <pre>
    *反馈文字内容。
    * </pre>
    *
    * <code>optional string feedbacktext = 1;</code>
    */
  public com.google.protobuf.ByteString getFeedbacktextBytes() 
  {
    java.lang.Object ref = feedbacktext_;
    if (ref instanceof java.lang.String) 
    {
      com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8( (java.lang.String) ref);
      feedbacktext_ = b;
      return b;
    }
    else 
    {
      return (com.google.protobuf.ByteString) ref;
    }
  }

    public static final int LOGZIPFILE_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString logzipfile_;
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
    /**
     * <pre>
     *压缩的日志文件。
     * </pre>
     *
     * <code>optional bytes logzipfile = 2;</code>
     */
    public com.google.protobuf.ByteString getLogzipfile() {
      return logzipfile_;
    }

    public static final int DIAGNOSEINFORMATION_FIELD_NUMBER = 3;
    private com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation diagnoseinformation_;
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
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 3;</code>
     */
    public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation getDiagnoseinformation() {
      return diagnoseinformation_ == null ? com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance() : diagnoseinformation_;
    }
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 3;</code>
     */
    public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder getDiagnoseinformationOrBuilder() {
      return diagnoseinformation_ == null ? com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance() : diagnoseinformation_;
    }

    public static final int EMAILADDRESS_FIELD_NUMBER = 4;
    private volatile java.lang.Object emailAddress_;
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
    public java.lang.String getEmailAddress() {
      java.lang.Object ref = emailAddress_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          emailAddress_ = s;
        }
        return s;
      }
    }
    /**
     * <pre>
     *用户的邮件地址。
     * </pre>
     *
     * <code>optional string emailAddress = 4;</code>
     */
    public com.google.protobuf.ByteString
        getEmailAddressBytes() {
      java.lang.Object ref = emailAddress_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        emailAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (hasDiagnoseinformation()) {
        if (!getDiagnoseinformation().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    private static final long serialVersionUID = 0L;
  }
