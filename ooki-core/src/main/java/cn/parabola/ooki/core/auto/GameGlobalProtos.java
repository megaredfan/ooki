// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: global.proto

package cn.parabola.ooki.core.auto;

public final class GameGlobalProtos {
  private GameGlobalProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code parabola.PlatformType}
   */
  public enum PlatformType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>IOS = 1;</code>
     */
    IOS(0, 1),
    /**
     * <code>UC = 2;</code>
     */
    UC(1, 2),
    /**
     * <code>XIAOMI = 3;</code>
     */
    XIAOMI(2, 3),
    /**
     * <code>A360 = 4;</code>
     */
    A360(3, 4),
    /**
     * <code>BAIDU = 5;</code>
     */
    BAIDU(4, 5),
    ;

    /**
     * <code>IOS = 1;</code>
     */
    public static final int IOS_VALUE = 1;
    /**
     * <code>UC = 2;</code>
     */
    public static final int UC_VALUE = 2;
    /**
     * <code>XIAOMI = 3;</code>
     */
    public static final int XIAOMI_VALUE = 3;
    /**
     * <code>A360 = 4;</code>
     */
    public static final int A360_VALUE = 4;
    /**
     * <code>BAIDU = 5;</code>
     */
    public static final int BAIDU_VALUE = 5;


    public final int getNumber() { return value; }

    public static PlatformType valueOf(int value) {
      switch (value) {
        case 1: return IOS;
        case 2: return UC;
        case 3: return XIAOMI;
        case 4: return A360;
        case 5: return BAIDU;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PlatformType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<PlatformType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PlatformType>() {
            public PlatformType findValueByNumber(int number) {
              return PlatformType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.parabola.ooki.core.auto.GameGlobalProtos.getDescriptor().getEnumTypes().get(0);
    }

    private static final PlatformType[] VALUES = values();

    public static PlatformType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private PlatformType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:parabola.PlatformType)
  }

  /**
   * Protobuf enum {@code parabola.BufferId}
   */
  public enum BufferId
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>Public = 1;</code>
     */
    Public(0, 1),
    ;

    /**
     * <code>Public = 1;</code>
     */
    public static final int Public_VALUE = 1;


    public final int getNumber() { return value; }

    public static BufferId valueOf(int value) {
      switch (value) {
        case 1: return Public;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<BufferId>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<BufferId>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<BufferId>() {
            public BufferId findValueByNumber(int number) {
              return BufferId.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.parabola.ooki.core.auto.GameGlobalProtos.getDescriptor().getEnumTypes().get(1);
    }

    private static final BufferId[] VALUES = values();

    public static BufferId valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private BufferId(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:parabola.BufferId)
  }

  /**
   * Protobuf enum {@code parabola.VersionStatus}
   */
  public enum VersionStatus
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>NORMAL = 1;</code>
     *
     * <pre>
     *正常登录
     * </pre>
     */
    NORMAL(0, 1),
    /**
     * <code>STOP = 2;</code>
     *
     * <pre>
     *服务器拒绝登录
     * </pre>
     */
    STOP(1, 2),
    /**
     * <code>UPDATE = 3;</code>
     *
     * <pre>
     *需要更新配置文件后进入游戏
     * </pre>
     */
    UPDATE(2, 3),
    ;

    /**
     * <code>NORMAL = 1;</code>
     *
     * <pre>
     *正常登录
     * </pre>
     */
    public static final int NORMAL_VALUE = 1;
    /**
     * <code>STOP = 2;</code>
     *
     * <pre>
     *服务器拒绝登录
     * </pre>
     */
    public static final int STOP_VALUE = 2;
    /**
     * <code>UPDATE = 3;</code>
     *
     * <pre>
     *需要更新配置文件后进入游戏
     * </pre>
     */
    public static final int UPDATE_VALUE = 3;


    public final int getNumber() { return value; }

    public static VersionStatus valueOf(int value) {
      switch (value) {
        case 1: return NORMAL;
        case 2: return STOP;
        case 3: return UPDATE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<VersionStatus>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<VersionStatus>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<VersionStatus>() {
            public VersionStatus findValueByNumber(int number) {
              return VersionStatus.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.parabola.ooki.core.auto.GameGlobalProtos.getDescriptor().getEnumTypes().get(2);
    }

    private static final VersionStatus[] VALUES = values();

    public static VersionStatus valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private VersionStatus(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:parabola.VersionStatus)
  }

  /**
   * Protobuf enum {@code parabola.MessageType}
   *
   * <pre>
   *标识属于哪个模块，1000以内的id留给ui线程间的消息id
   * </pre>
   */
  public enum MessageType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>MSG_ID_LOGIN = 1000;</code>
     *
     * <pre>
     *登陆，以及版本检查
     * </pre>
     */
    MSG_ID_LOGIN(0, 1000),
    /**
     * <code>MSG_ID_CHECK_VERSION = 1001;</code>
     *
     * <pre>
     *检查版本号
     * </pre>
     */
    MSG_ID_CHECK_VERSION(1, 1001),
    /**
     * <code>MSG_ID_PING = 1002;</code>
     *
     * <pre>
     *ping
     * </pre>
     */
    MSG_ID_PING(2, 1002),
    /**
     * <code>MSG_ID_BIND_ACCOUNT = 1003;</code>
     *
     * <pre>
     *bind account
     * </pre>
     */
    MSG_ID_BIND_ACCOUNT(3, 1003),
    /**
     * <code>MSG_ID_CHECK_GAMECENTER = 1004;</code>
     *
     * <pre>
     *check ios game center
     * </pre>
     */
    MSG_ID_CHECK_GAMECENTER(4, 1004),
    /**
     * <code>MSG_ID_PAYMENT = 1005;</code>
     *
     * <pre>
     * 支付
     * </pre>
     */
    MSG_ID_PAYMENT(5, 1005),
    /**
     * <code>MSG_ID_FUNCTION_SWITCH = 1006;</code>
     *
     * <pre>
     *功能显示开关
     * </pre>
     */
    MSG_ID_FUNCTION_SWITCH(6, 1006),
    /**
     * <code>MSG_ID_CREATE_PLAYER = 1007;</code>
     *
     * <pre>
     *创建角色
     * </pre>
     */
    MSG_ID_CREATE_PLAYER(7, 1007),
    /**
     * <code>MSG_ID_NEW_PLAYER_PROGRESS = 1008;</code>
     *
     * <pre>
     *新手指导进度
     * </pre>
     */
    MSG_ID_NEW_PLAYER_PROGRESS(8, 1008),
    ;

    /**
     * <code>MSG_ID_LOGIN = 1000;</code>
     *
     * <pre>
     *登陆，以及版本检查
     * </pre>
     */
    public static final int MSG_ID_LOGIN_VALUE = 1000;
    /**
     * <code>MSG_ID_CHECK_VERSION = 1001;</code>
     *
     * <pre>
     *检查版本号
     * </pre>
     */
    public static final int MSG_ID_CHECK_VERSION_VALUE = 1001;
    /**
     * <code>MSG_ID_PING = 1002;</code>
     *
     * <pre>
     *ping
     * </pre>
     */
    public static final int MSG_ID_PING_VALUE = 1002;
    /**
     * <code>MSG_ID_BIND_ACCOUNT = 1003;</code>
     *
     * <pre>
     *bind account
     * </pre>
     */
    public static final int MSG_ID_BIND_ACCOUNT_VALUE = 1003;
    /**
     * <code>MSG_ID_CHECK_GAMECENTER = 1004;</code>
     *
     * <pre>
     *check ios game center
     * </pre>
     */
    public static final int MSG_ID_CHECK_GAMECENTER_VALUE = 1004;
    /**
     * <code>MSG_ID_PAYMENT = 1005;</code>
     *
     * <pre>
     * 支付
     * </pre>
     */
    public static final int MSG_ID_PAYMENT_VALUE = 1005;
    /**
     * <code>MSG_ID_FUNCTION_SWITCH = 1006;</code>
     *
     * <pre>
     *功能显示开关
     * </pre>
     */
    public static final int MSG_ID_FUNCTION_SWITCH_VALUE = 1006;
    /**
     * <code>MSG_ID_CREATE_PLAYER = 1007;</code>
     *
     * <pre>
     *创建角色
     * </pre>
     */
    public static final int MSG_ID_CREATE_PLAYER_VALUE = 1007;
    /**
     * <code>MSG_ID_NEW_PLAYER_PROGRESS = 1008;</code>
     *
     * <pre>
     *新手指导进度
     * </pre>
     */
    public static final int MSG_ID_NEW_PLAYER_PROGRESS_VALUE = 1008;


    public final int getNumber() { return value; }

    public static MessageType valueOf(int value) {
      switch (value) {
        case 1000: return MSG_ID_LOGIN;
        case 1001: return MSG_ID_CHECK_VERSION;
        case 1002: return MSG_ID_PING;
        case 1003: return MSG_ID_BIND_ACCOUNT;
        case 1004: return MSG_ID_CHECK_GAMECENTER;
        case 1005: return MSG_ID_PAYMENT;
        case 1006: return MSG_ID_FUNCTION_SWITCH;
        case 1007: return MSG_ID_CREATE_PLAYER;
        case 1008: return MSG_ID_NEW_PLAYER_PROGRESS;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MessageType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<MessageType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MessageType>() {
            public MessageType findValueByNumber(int number) {
              return MessageType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.parabola.ooki.core.auto.GameGlobalProtos.getDescriptor().getEnumTypes().get(3);
    }

    private static final MessageType[] VALUES = values();

    public static MessageType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private MessageType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:parabola.MessageType)
  }

  /**
   * Protobuf enum {@code parabola.MessageCode}
   *
   * <pre>
   *错误码
   * </pre>
   */
  public enum MessageCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>OK = 1;</code>
     */
    OK(0, 1),
    /**
     * <code>ERR = 2;</code>
     */
    ERR(1, 2),
    /**
     * <code>NOT_FOUND_PLAYER = 3;</code>
     *
     * <pre>
     *没有这个玩家
     * </pre>
     */
    NOT_FOUND_PLAYER(2, 3),
    /**
     * <code>SESSION_EXPIRE = 4;</code>
     *
     * <pre>
     *会话过期
     * </pre>
     */
    SESSION_EXPIRE(3, 4),
    /**
     * <code>NOT_ENOUGH_LEVEL = 5;</code>
     *
     * <pre>
     *等级不够
     * </pre>
     */
    NOT_ENOUGH_LEVEL(4, 5),
    /**
     * <code>NOT_NEED_SPEEDY = 6;</code>
     *
     * <pre>
     *不需要加速，已完成
     * </pre>
     */
    NOT_NEED_SPEEDY(5, 6),
    /**
     * <code>HAD_MAX_LEVEL = 7;</code>
     *
     * <pre>
     *已经达到最大等级
     * </pre>
     */
    HAD_MAX_LEVEL(6, 7),
    /**
     * <code>DATA_EXPIRE = 8;</code>
     *
     * <pre>
     *客户端存储的playerId已过期，需要删除，重新创建角色。因为服务器端已删除了这个用户的数据
     * </pre>
     */
    DATA_EXPIRE(7, 8),
    /**
     * <code>INVALID_NAME = 9;</code>
     *
     * <pre>
     *非法的名字
     * </pre>
     */
    INVALID_NAME(8, 9),
    ;

    /**
     * <code>OK = 1;</code>
     */
    public static final int OK_VALUE = 1;
    /**
     * <code>ERR = 2;</code>
     */
    public static final int ERR_VALUE = 2;
    /**
     * <code>NOT_FOUND_PLAYER = 3;</code>
     *
     * <pre>
     *没有这个玩家
     * </pre>
     */
    public static final int NOT_FOUND_PLAYER_VALUE = 3;
    /**
     * <code>SESSION_EXPIRE = 4;</code>
     *
     * <pre>
     *会话过期
     * </pre>
     */
    public static final int SESSION_EXPIRE_VALUE = 4;
    /**
     * <code>NOT_ENOUGH_LEVEL = 5;</code>
     *
     * <pre>
     *等级不够
     * </pre>
     */
    public static final int NOT_ENOUGH_LEVEL_VALUE = 5;
    /**
     * <code>NOT_NEED_SPEEDY = 6;</code>
     *
     * <pre>
     *不需要加速，已完成
     * </pre>
     */
    public static final int NOT_NEED_SPEEDY_VALUE = 6;
    /**
     * <code>HAD_MAX_LEVEL = 7;</code>
     *
     * <pre>
     *已经达到最大等级
     * </pre>
     */
    public static final int HAD_MAX_LEVEL_VALUE = 7;
    /**
     * <code>DATA_EXPIRE = 8;</code>
     *
     * <pre>
     *客户端存储的playerId已过期，需要删除，重新创建角色。因为服务器端已删除了这个用户的数据
     * </pre>
     */
    public static final int DATA_EXPIRE_VALUE = 8;
    /**
     * <code>INVALID_NAME = 9;</code>
     *
     * <pre>
     *非法的名字
     * </pre>
     */
    public static final int INVALID_NAME_VALUE = 9;


    public final int getNumber() { return value; }

    public static MessageCode valueOf(int value) {
      switch (value) {
        case 1: return OK;
        case 2: return ERR;
        case 3: return NOT_FOUND_PLAYER;
        case 4: return SESSION_EXPIRE;
        case 5: return NOT_ENOUGH_LEVEL;
        case 6: return NOT_NEED_SPEEDY;
        case 7: return HAD_MAX_LEVEL;
        case 8: return DATA_EXPIRE;
        case 9: return INVALID_NAME;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MessageCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<MessageCode>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MessageCode>() {
            public MessageCode findValueByNumber(int number) {
              return MessageCode.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return cn.parabola.ooki.core.auto.GameGlobalProtos.getDescriptor().getEnumTypes().get(4);
    }

    private static final MessageCode[] VALUES = values();

    public static MessageCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private MessageCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:parabola.MessageCode)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014global.proto\022\010parabola*@\n\014PlatformType" +
      "\022\007\n\003IOS\020\001\022\006\n\002UC\020\002\022\n\n\006XIAOMI\020\003\022\010\n\004A360\020\004\022" +
      "\t\n\005BAIDU\020\005*\026\n\010BufferId\022\n\n\006Public\020\001*1\n\rVe" +
      "rsionStatus\022\n\n\006NORMAL\020\001\022\010\n\004STOP\020\002\022\n\n\006UPD" +
      "ATE\020\003*\363\001\n\013MessageType\022\021\n\014MSG_ID_LOGIN\020\350\007" +
      "\022\031\n\024MSG_ID_CHECK_VERSION\020\351\007\022\020\n\013MSG_ID_PI" +
      "NG\020\352\007\022\030\n\023MSG_ID_BIND_ACCOUNT\020\353\007\022\034\n\027MSG_I" +
      "D_CHECK_GAMECENTER\020\354\007\022\023\n\016MSG_ID_PAYMENT\020" +
      "\355\007\022\033\n\026MSG_ID_FUNCTION_SWITCH\020\356\007\022\031\n\024MSG_I" +
      "D_CREATE_PLAYER\020\357\007\022\037\n\032MSG_ID_NEW_PLAYER_",
      "PROGRESS\020\360\007*\251\001\n\013MessageCode\022\006\n\002OK\020\001\022\007\n\003E" +
      "RR\020\002\022\024\n\020NOT_FOUND_PLAYER\020\003\022\022\n\016SESSION_EX" +
      "PIRE\020\004\022\024\n\020NOT_ENOUGH_LEVEL\020\005\022\023\n\017NOT_NEED" +
      "_SPEEDY\020\006\022\021\n\rHAD_MAX_LEVEL\020\007\022\017\n\013DATA_EXP" +
      "IRE\020\010\022\020\n\014INVALID_NAME\020\tB.\n\032cn.parabola.o" +
      "oki.core.autoB\020GameGlobalProtos"
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
  }

  // @@protoc_insertion_point(outer_class_scope)
}
