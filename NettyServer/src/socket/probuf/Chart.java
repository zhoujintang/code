// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chart.proto

package socket.probuf;

public final class Chart {
  private Chart() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqLoginOrBuilder extends
      // @@protoc_insertion_point(interface_extends:socket.probuf.ReqLogin)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string str_userid = 1;</code>
     */
    boolean hasStrUserid();
    /**
     * <code>optional string str_userid = 1;</code>
     */
    java.lang.String getStrUserid();
    /**
     * <code>optional string str_userid = 1;</code>
     */
    com.google.protobuf.ByteString
        getStrUseridBytes();

    /**
     * <code>optional int32 int32_oemid = 2;</code>
     */
    boolean hasInt32Oemid();
    /**
     * <code>optional int32 int32_oemid = 2;</code>
     */
    int getInt32Oemid();
  }
  /**
   * Protobuf type {@code socket.probuf.ReqLogin}
   */
  public static final class ReqLogin extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:socket.probuf.ReqLogin)
      ReqLoginOrBuilder {
    // Use ReqLogin.newBuilder() to construct.
    private ReqLogin(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReqLogin(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReqLogin defaultInstance;
    public static ReqLogin getDefaultInstance() {
      return defaultInstance;
    }

    public ReqLogin getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReqLogin(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
              strUserid_ = bs;
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              int32Oemid_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return socket.probuf.Chart.internal_static_socket_probuf_ReqLogin_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return socket.probuf.Chart.internal_static_socket_probuf_ReqLogin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              socket.probuf.Chart.ReqLogin.class, socket.probuf.Chart.ReqLogin.Builder.class);
    }

    public static com.google.protobuf.Parser<ReqLogin> PARSER =
        new com.google.protobuf.AbstractParser<ReqLogin>() {
      public ReqLogin parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqLogin(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReqLogin> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int STR_USERID_FIELD_NUMBER = 1;
    private java.lang.Object strUserid_;
    /**
     * <code>optional string str_userid = 1;</code>
     */
    public boolean hasStrUserid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional string str_userid = 1;</code>
     */
    public java.lang.String getStrUserid() {
      java.lang.Object ref = strUserid_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          strUserid_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string str_userid = 1;</code>
     */
    public com.google.protobuf.ByteString
        getStrUseridBytes() {
      java.lang.Object ref = strUserid_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        strUserid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int INT32_OEMID_FIELD_NUMBER = 2;
    private int int32Oemid_;
    /**
     * <code>optional int32 int32_oemid = 2;</code>
     */
    public boolean hasInt32Oemid() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 int32_oemid = 2;</code>
     */
    public int getInt32Oemid() {
      return int32Oemid_;
    }

    private void initFields() {
      strUserid_ = "";
      int32Oemid_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getStrUseridBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, int32Oemid_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getStrUseridBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, int32Oemid_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static socket.probuf.Chart.ReqLogin parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static socket.probuf.Chart.ReqLogin parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static socket.probuf.Chart.ReqLogin parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static socket.probuf.Chart.ReqLogin parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(socket.probuf.Chart.ReqLogin prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code socket.probuf.ReqLogin}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:socket.probuf.ReqLogin)
        socket.probuf.Chart.ReqLoginOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return socket.probuf.Chart.internal_static_socket_probuf_ReqLogin_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return socket.probuf.Chart.internal_static_socket_probuf_ReqLogin_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                socket.probuf.Chart.ReqLogin.class, socket.probuf.Chart.ReqLogin.Builder.class);
      }

      // Construct using socket.probuf.Chart.ReqLogin.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        strUserid_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        int32Oemid_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return socket.probuf.Chart.internal_static_socket_probuf_ReqLogin_descriptor;
      }

      public socket.probuf.Chart.ReqLogin getDefaultInstanceForType() {
        return socket.probuf.Chart.ReqLogin.getDefaultInstance();
      }

      public socket.probuf.Chart.ReqLogin build() {
        socket.probuf.Chart.ReqLogin result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public socket.probuf.Chart.ReqLogin buildPartial() {
        socket.probuf.Chart.ReqLogin result = new socket.probuf.Chart.ReqLogin(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.strUserid_ = strUserid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.int32Oemid_ = int32Oemid_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof socket.probuf.Chart.ReqLogin) {
          return mergeFrom((socket.probuf.Chart.ReqLogin)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(socket.probuf.Chart.ReqLogin other) {
        if (other == socket.probuf.Chart.ReqLogin.getDefaultInstance()) return this;
        if (other.hasStrUserid()) {
          bitField0_ |= 0x00000001;
          strUserid_ = other.strUserid_;
          onChanged();
        }
        if (other.hasInt32Oemid()) {
          setInt32Oemid(other.getInt32Oemid());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        socket.probuf.Chart.ReqLogin parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (socket.probuf.Chart.ReqLogin) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object strUserid_ = "";
      /**
       * <code>optional string str_userid = 1;</code>
       */
      public boolean hasStrUserid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional string str_userid = 1;</code>
       */
      public java.lang.String getStrUserid() {
        java.lang.Object ref = strUserid_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            strUserid_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string str_userid = 1;</code>
       */
      public com.google.protobuf.ByteString
          getStrUseridBytes() {
        java.lang.Object ref = strUserid_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          strUserid_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string str_userid = 1;</code>
       */
      public Builder setStrUserid(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        strUserid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string str_userid = 1;</code>
       */
      public Builder clearStrUserid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        strUserid_ = getDefaultInstance().getStrUserid();
        onChanged();
        return this;
      }
      /**
       * <code>optional string str_userid = 1;</code>
       */
      public Builder setStrUseridBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        strUserid_ = value;
        onChanged();
        return this;
      }

      private int int32Oemid_ ;
      /**
       * <code>optional int32 int32_oemid = 2;</code>
       */
      public boolean hasInt32Oemid() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 int32_oemid = 2;</code>
       */
      public int getInt32Oemid() {
        return int32Oemid_;
      }
      /**
       * <code>optional int32 int32_oemid = 2;</code>
       */
      public Builder setInt32Oemid(int value) {
        bitField0_ |= 0x00000002;
        int32Oemid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 int32_oemid = 2;</code>
       */
      public Builder clearInt32Oemid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        int32Oemid_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:socket.probuf.ReqLogin)
    }

    static {
      defaultInstance = new ReqLogin(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:socket.probuf.ReqLogin)
  }

  public interface RspLoginOrBuilder extends
      // @@protoc_insertion_point(interface_extends:socket.probuf.RspLogin)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 int32_code = 1;</code>
     */
    boolean hasInt32Code();
    /**
     * <code>optional int32 int32_code = 1;</code>
     */
    int getInt32Code();

    /**
     * <code>optional string str_msg = 2;</code>
     */
    boolean hasStrMsg();
    /**
     * <code>optional string str_msg = 2;</code>
     */
    java.lang.String getStrMsg();
    /**
     * <code>optional string str_msg = 2;</code>
     */
    com.google.protobuf.ByteString
        getStrMsgBytes();
  }
  /**
   * Protobuf type {@code socket.probuf.RspLogin}
   */
  public static final class RspLogin extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:socket.probuf.RspLogin)
      RspLoginOrBuilder {
    // Use RspLogin.newBuilder() to construct.
    private RspLogin(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RspLogin(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RspLogin defaultInstance;
    public static RspLogin getDefaultInstance() {
      return defaultInstance;
    }

    public RspLogin getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RspLogin(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
            case 8: {
              bitField0_ |= 0x00000001;
              int32Code_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              strMsg_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return socket.probuf.Chart.internal_static_socket_probuf_RspLogin_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return socket.probuf.Chart.internal_static_socket_probuf_RspLogin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              socket.probuf.Chart.RspLogin.class, socket.probuf.Chart.RspLogin.Builder.class);
    }

    public static com.google.protobuf.Parser<RspLogin> PARSER =
        new com.google.protobuf.AbstractParser<RspLogin>() {
      public RspLogin parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RspLogin(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RspLogin> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int INT32_CODE_FIELD_NUMBER = 1;
    private int int32Code_;
    /**
     * <code>optional int32 int32_code = 1;</code>
     */
    public boolean hasInt32Code() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 int32_code = 1;</code>
     */
    public int getInt32Code() {
      return int32Code_;
    }

    public static final int STR_MSG_FIELD_NUMBER = 2;
    private java.lang.Object strMsg_;
    /**
     * <code>optional string str_msg = 2;</code>
     */
    public boolean hasStrMsg() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string str_msg = 2;</code>
     */
    public java.lang.String getStrMsg() {
      java.lang.Object ref = strMsg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          strMsg_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string str_msg = 2;</code>
     */
    public com.google.protobuf.ByteString
        getStrMsgBytes() {
      java.lang.Object ref = strMsg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        strMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      int32Code_ = 0;
      strMsg_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, int32Code_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getStrMsgBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, int32Code_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getStrMsgBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static socket.probuf.Chart.RspLogin parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static socket.probuf.Chart.RspLogin parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static socket.probuf.Chart.RspLogin parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static socket.probuf.Chart.RspLogin parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(socket.probuf.Chart.RspLogin prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code socket.probuf.RspLogin}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:socket.probuf.RspLogin)
        socket.probuf.Chart.RspLoginOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return socket.probuf.Chart.internal_static_socket_probuf_RspLogin_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return socket.probuf.Chart.internal_static_socket_probuf_RspLogin_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                socket.probuf.Chart.RspLogin.class, socket.probuf.Chart.RspLogin.Builder.class);
      }

      // Construct using socket.probuf.Chart.RspLogin.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        int32Code_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        strMsg_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return socket.probuf.Chart.internal_static_socket_probuf_RspLogin_descriptor;
      }

      public socket.probuf.Chart.RspLogin getDefaultInstanceForType() {
        return socket.probuf.Chart.RspLogin.getDefaultInstance();
      }

      public socket.probuf.Chart.RspLogin build() {
        socket.probuf.Chart.RspLogin result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public socket.probuf.Chart.RspLogin buildPartial() {
        socket.probuf.Chart.RspLogin result = new socket.probuf.Chart.RspLogin(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.int32Code_ = int32Code_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.strMsg_ = strMsg_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof socket.probuf.Chart.RspLogin) {
          return mergeFrom((socket.probuf.Chart.RspLogin)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(socket.probuf.Chart.RspLogin other) {
        if (other == socket.probuf.Chart.RspLogin.getDefaultInstance()) return this;
        if (other.hasInt32Code()) {
          setInt32Code(other.getInt32Code());
        }
        if (other.hasStrMsg()) {
          bitField0_ |= 0x00000002;
          strMsg_ = other.strMsg_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        socket.probuf.Chart.RspLogin parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (socket.probuf.Chart.RspLogin) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int int32Code_ ;
      /**
       * <code>optional int32 int32_code = 1;</code>
       */
      public boolean hasInt32Code() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 int32_code = 1;</code>
       */
      public int getInt32Code() {
        return int32Code_;
      }
      /**
       * <code>optional int32 int32_code = 1;</code>
       */
      public Builder setInt32Code(int value) {
        bitField0_ |= 0x00000001;
        int32Code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 int32_code = 1;</code>
       */
      public Builder clearInt32Code() {
        bitField0_ = (bitField0_ & ~0x00000001);
        int32Code_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object strMsg_ = "";
      /**
       * <code>optional string str_msg = 2;</code>
       */
      public boolean hasStrMsg() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string str_msg = 2;</code>
       */
      public java.lang.String getStrMsg() {
        java.lang.Object ref = strMsg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            strMsg_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string str_msg = 2;</code>
       */
      public com.google.protobuf.ByteString
          getStrMsgBytes() {
        java.lang.Object ref = strMsg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          strMsg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string str_msg = 2;</code>
       */
      public Builder setStrMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        strMsg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string str_msg = 2;</code>
       */
      public Builder clearStrMsg() {
        bitField0_ = (bitField0_ & ~0x00000002);
        strMsg_ = getDefaultInstance().getStrMsg();
        onChanged();
        return this;
      }
      /**
       * <code>optional string str_msg = 2;</code>
       */
      public Builder setStrMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        strMsg_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:socket.probuf.RspLogin)
    }

    static {
      defaultInstance = new RspLogin(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:socket.probuf.RspLogin)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_socket_probuf_ReqLogin_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_socket_probuf_ReqLogin_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_socket_probuf_RspLogin_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_socket_probuf_RspLogin_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013chart.proto\022\rsocket.probuf\"3\n\010ReqLogin" +
      "\022\022\n\nstr_userid\030\001 \001(\t\022\023\n\013int32_oemid\030\002 \001(" +
      "\005\"/\n\010RspLogin\022\022\n\nint32_code\030\001 \001(\005\022\017\n\007str" +
      "_msg\030\002 \001(\t"
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
        }, assigner);
    internal_static_socket_probuf_ReqLogin_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_socket_probuf_ReqLogin_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_socket_probuf_ReqLogin_descriptor,
        new java.lang.String[] { "StrUserid", "Int32Oemid", });
    internal_static_socket_probuf_RspLogin_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_socket_probuf_RspLogin_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_socket_probuf_RspLogin_descriptor,
        new java.lang.String[] { "Int32Code", "StrMsg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
