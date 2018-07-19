LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

# target
TARGET_NAME = NFCJni
TARGET_PATH = ../JniLibs/$(TARGET_ARCH_ABI)

# source files
SRC_PATH += ./

# include
INCLUDE_PATH = prebuilt/include

# search the lib which complied by myself
LIB_PATH = prebuilt/$(TARGET_ARCH_ABI)

# library names
LIB_NAME = libskf.so

#第三方的编译模块
include $(CLEAR_VARS)

LOCAL_MODULE    := libskfMoudle
#$(TARGET_ARCH_ABI),用于编译不同的平台SO文件
LOCAL_SRC_FILES := $(LIB_PATH)/$(LIB_NAME)
$(warning Include lib: $(LOCAL_SRC_FILES))
#下面是申明第三方头文件路径
LOCAL_EXPORT_C_INCLUDES := $(INCLUDE_PATH)
$(warning Include head: $(LOCAL_EXPORT_C_INCLUDES))
NDK_APP_DST_DIR      := $(addprefix $(LOCAL_PATH)/, $(TARGET_PATH)/)
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
# c files
C_FILES := $(wildcard $(LOCAL_PATH)/*.c)

# c flags
LOCAL_CFLAGS         := #-Werror -DANDROID -DLOG
LOCAL_LDLIBS         += -L$(SYSROOT)/usr/lib -llog

# source files
LOCAL_SRC_FILES := $(C_FILES:$(LOCAL_PATH)/%=%)

# library name
LOCAL_MODULE         := $(TARGET_NAME)
NDK_APP_OUT          := $(addprefix $(LOCAL_PATH)/, $(TARGET_PATH)/obj)
NDK_APP_DST_DIR      := $(addprefix $(LOCAL_PATH)/, $(TARGET_PATH)/)
#这里引入第三方编译模块
LOCAL_SHARED_LIBRARIES := libskfMoudle
include $(BUILD_SHARED_LIBRARY)
