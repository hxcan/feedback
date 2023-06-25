// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: systemFileListMessage.proto

package com.stupidbeauty.comgooglewidevinesoftwaredrmremover;

public final class SystemFileListMessageProtos {
  private SystemFileListMessageProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SystemFileListMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *反馈文字内容。
     * </pre>
     *
     * <code>optional string feedbacktext = 1;</code>
     */
    boolean hasFeedbacktext();
    /**
     * <pre>
     *反馈文字内容。
     * </pre>
     *
     * <code>optional string feedbacktext = 1;</code>
     */
    java.lang.String getFeedbacktext();
    /**
     * <pre>
     *反馈文字内容。
     * </pre>
     *
     * <code>optional string feedbacktext = 1;</code>
     */
    com.google.protobuf.ByteString
        getFeedbacktextBytes();

    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
     */
    boolean hasDiagnoseinformation();
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
     */
    com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation getDiagnoseinformation();
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
     */
    com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder getDiagnoseinformationOrBuilder();
  }
  /**
   * Protobuf type {@code com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage}
   */
  public  static final class SystemFileListMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage)
      SystemFileListMessageOrBuilder {
    // Use SystemFileListMessage.newBuilder() to construct.
    private SystemFileListMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SystemFileListMessage() {
      feedbacktext_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private SystemFileListMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              feedbacktext_ = bs;
              break;
            }
            case 18: {
              com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.Builder subBuilder = null;
              if (((bitField0_ & 0x00000002) == 0x00000002)) {
                subBuilder = diagnoseinformation_.toBuilder();
              }
              diagnoseinformation_ = input.readMessage(com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(diagnoseinformation_);
                diagnoseinformation_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000002;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.class, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.Builder.class);
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
    public boolean hasFeedbacktext() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *反馈文字内容。
     * </pre>
     *
     * <code>optional string feedbacktext = 1;</code>
     */
    public java.lang.String getFeedbacktext() {
      java.lang.Object ref = feedbacktext_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
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
    public com.google.protobuf.ByteString
        getFeedbacktextBytes() {
      java.lang.Object ref = feedbacktext_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        feedbacktext_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int DIAGNOSEINFORMATION_FIELD_NUMBER = 2;
    private com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation diagnoseinformation_;
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
     */
    public boolean hasDiagnoseinformation() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
     */
    public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation getDiagnoseinformation() {
      return diagnoseinformation_ == null ? com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance() : diagnoseinformation_;
    }
    /**
     * <pre>
     *诊断信息。
     * </pre>
     *
     * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
     */
    public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder getDiagnoseinformationOrBuilder() {
      return diagnoseinformation_ == null ? com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance() : diagnoseinformation_;
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

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, feedbacktext_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeMessage(2, getDiagnoseinformation());
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, feedbacktext_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, getDiagnoseinformation());
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage)) {
        return super.equals(obj);
      }
      com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage other = (com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage) obj;

      boolean result = true;
      result = result && (hasFeedbacktext() == other.hasFeedbacktext());
      if (hasFeedbacktext()) {
        result = result && getFeedbacktext()
            .equals(other.getFeedbacktext());
      }
      result = result && (hasDiagnoseinformation() == other.hasDiagnoseinformation());
      if (hasDiagnoseinformation()) {
        result = result && getDiagnoseinformation()
            .equals(other.getDiagnoseinformation());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasFeedbacktext()) {
        hash = (37 * hash) + FEEDBACKTEXT_FIELD_NUMBER;
        hash = (53 * hash) + getFeedbacktext().hashCode();
      }
      if (hasDiagnoseinformation()) {
        hash = (37 * hash) + DIAGNOSEINFORMATION_FIELD_NUMBER;
        hash = (53 * hash) + getDiagnoseinformation().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage)
        com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.class, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.Builder.class);
      }

      // Construct using com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
          getDiagnoseinformationFieldBuilder();
        }
      }
      public Builder clear() {
        super.clear();
        feedbacktext_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        if (diagnoseinformationBuilder_ == null) {
          diagnoseinformation_ = null;
        } else {
          diagnoseinformationBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_descriptor;
      }

      public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage getDefaultInstanceForType() {
        return com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.getDefaultInstance();
      }

      public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage build() {
        com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage buildPartial() {
        com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage result = new com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.feedbacktext_ = feedbacktext_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        if (diagnoseinformationBuilder_ == null) {
          result.diagnoseinformation_ = diagnoseinformation_;
        } else {
          result.diagnoseinformation_ = diagnoseinformationBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage) {
          return mergeFrom((com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage other) {
        if (other == com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage.getDefaultInstance()) return this;
        if (other.hasFeedbacktext()) {
          bitField0_ |= 0x00000001;
          feedbacktext_ = other.feedbacktext_;
          onChanged();
        }
        if (other.hasDiagnoseinformation()) {
          mergeDiagnoseinformation(other.getDiagnoseinformation());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (hasDiagnoseinformation()) {
          if (!getDiagnoseinformation().isInitialized()) {
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object feedbacktext_ = "";
      /**
       * <pre>
       *反馈文字内容。
       * </pre>
       *
       * <code>optional string feedbacktext = 1;</code>
       */
      public boolean hasFeedbacktext() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <pre>
       *反馈文字内容。
       * </pre>
       *
       * <code>optional string feedbacktext = 1;</code>
       */
      public java.lang.String getFeedbacktext() {
        java.lang.Object ref = feedbacktext_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            feedbacktext_ = s;
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
      public com.google.protobuf.ByteString
          getFeedbacktextBytes() {
        java.lang.Object ref = feedbacktext_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          feedbacktext_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *反馈文字内容。
       * </pre>
       *
       * <code>optional string feedbacktext = 1;</code>
       */
      public Builder setFeedbacktext(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        feedbacktext_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *反馈文字内容。
       * </pre>
       *
       * <code>optional string feedbacktext = 1;</code>
       */
      public Builder clearFeedbacktext() {
        bitField0_ = (bitField0_ & ~0x00000001);
        feedbacktext_ = getDefaultInstance().getFeedbacktext();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *反馈文字内容。
       * </pre>
       *
       * <code>optional string feedbacktext = 1;</code>
       */
      public Builder setFeedbacktextBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        feedbacktext_ = value;
        onChanged();
        return this;
      }

      private com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation diagnoseinformation_ = null;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.Builder, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder> diagnoseinformationBuilder_;
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public boolean hasDiagnoseinformation() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation getDiagnoseinformation() {
        if (diagnoseinformationBuilder_ == null) {
          return diagnoseinformation_ == null ? com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance() : diagnoseinformation_;
        } else {
          return diagnoseinformationBuilder_.getMessage();
        }
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public Builder setDiagnoseinformation(com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation value) {
        if (diagnoseinformationBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          diagnoseinformation_ = value;
          onChanged();
        } else {
          diagnoseinformationBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public Builder setDiagnoseinformation(
          com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.Builder builderForValue) {
        if (diagnoseinformationBuilder_ == null) {
          diagnoseinformation_ = builderForValue.build();
          onChanged();
        } else {
          diagnoseinformationBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public Builder mergeDiagnoseinformation(com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation value) {
        if (diagnoseinformationBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              diagnoseinformation_ != null &&
              diagnoseinformation_ != com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance()) {
            diagnoseinformation_ =
              com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.newBuilder(diagnoseinformation_).mergeFrom(value).buildPartial();
          } else {
            diagnoseinformation_ = value;
          }
          onChanged();
        } else {
          diagnoseinformationBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public Builder clearDiagnoseinformation() {
        if (diagnoseinformationBuilder_ == null) {
          diagnoseinformation_ = null;
          onChanged();
        } else {
          diagnoseinformationBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.Builder getDiagnoseinformationBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getDiagnoseinformationFieldBuilder().getBuilder();
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder getDiagnoseinformationOrBuilder() {
        if (diagnoseinformationBuilder_ != null) {
          return diagnoseinformationBuilder_.getMessageOrBuilder();
        } else {
          return diagnoseinformation_ == null ?
              com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.getDefaultInstance() : diagnoseinformation_;
        }
      }
      /**
       * <pre>
       *诊断信息。
       * </pre>
       *
       * <code>optional .com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformation diagnoseinformation = 2;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.Builder, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder> 
          getDiagnoseinformationFieldBuilder() {
        if (diagnoseinformationBuilder_ == null) {
          diagnoseinformationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformation.Builder, com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.DiagnoseInformationOrBuilder>(
                  getDiagnoseinformation(),
                  getParentForChildren(),
                  isClean());
          diagnoseinformation_ = null;
        }
        return diagnoseinformationBuilder_;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage)
    }

    // @@protoc_insertion_point(class_scope:com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessage)
    private static final com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage();
    }

    public static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<SystemFileListMessage>
        PARSER = new com.google.protobuf.AbstractParser<SystemFileListMessage>() {
      public SystemFileListMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new SystemFileListMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SystemFileListMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SystemFileListMessage> getParserForType() {
      return PARSER;
    }

    public com.stupidbeauty.comgooglewidevinesoftwaredrmremover.SystemFileListMessageProtos.SystemFileListMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033systemFileListMessage.proto\0224com.stupi" +
      "dbeauty.comgooglewidevinesoftwaredrmremo" +
      "ver\032\031diagnoseinformation.proto\"\225\001\n\025Syste" +
      "mFileListMessage\022\024\n\014feedbacktext\030\001 \001(\t\022f" +
      "\n\023diagnoseinformation\030\002 \001(\0132I.com.stupid" +
      "beauty.comgooglewidevinesoftwaredrmremov" +
      "er.DiagnoseInformationBS\n4com.stupidbeau" +
      "ty.comgooglewidevinesoftwaredrmremoverB\033" +
      "SystemFileListMessageProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.getDescriptor(),
        }, assigner);
    internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_comgooglewidevinesoftwaredrmremover_SystemFileListMessage_descriptor,
        new java.lang.String[] { "Feedbacktext", "Diagnoseinformation", });
    com.stupidbeauty.comgooglewidevinesoftwaredrmremover.DiagnoseInformationProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
