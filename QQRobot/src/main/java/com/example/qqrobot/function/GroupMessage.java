package com.example.qqrobot.function;

import com.example.qqrobot.entity.Message;
import com.example.qqrobot.tool.pretreatment;
import org.jetbrains.annotations.NotNull;

public class GroupMessage {
    static String message1 = null;
    static String AimQQ = "3455924379";
    public static void group_talk(@NotNull Message message) {
        if (message.getRaw_message().contains("生日快乐")){
            String numberFromString = pretreatment.getNumberFromString(message.getRaw_message());
            CQ_CodeTalk.comment(false,"gift",message.getUser_id(),message.getGroup_id(),numberFromString);
        }
        if (message.getSender().getUser_id().contains(AimQQ)) {
            //ToHostAndOtherTalk.BanSendMessage(message);//对AimQQ禁言
            ToHostAndOtherTalk.deleteMessage(message);//对AimQQ发送的群消息撤回,自身要有管理权限
        CQ_CodeTalk.comment(false,"poke",message.getUser_id(),message.getGroup_id(),null);
        } else {
            if (message.getRaw_message().contains("[CQ:at,qq=3307211295]")) {
                message1 = "@幕落人孤寂 爸爸~爸爸~" + message.getSender().getNickname() + "有事找你";
                ToHostAndOtherTalk.toTalkWithGroupId(message.getGroup_id(), message1);
            }
            if (message.getRaw_message().contains("[CQ:at,qq=3168352498]")) {
                if (message.getSender().getUser_id().contains("3307211295")) {
                    message1 = "爸爸，我一直我一直期盼着和您对话";
                    ToHostAndOtherTalk.toTalkWithGroupId(message.getGroup_id(), message1);
                } else {
                    if (message.getRaw_message().contains("主人")) {
                        message1 = "@" + message.getSender().getNickname() + " " + "听不懂你在说啥，如果真有问题请找我爸爸@幕落人孤寂";
                        ToHostAndOtherTalk.toTalkWithGroupId(message.getGroup_id(), message1);
                    } else {
                       message1 = "@" + message.getSender().getNickname() + " " + "叔叔，有什么我可以为你效劳的吗？";
                        ToHostAndOtherTalk.toTalkWithGroupId(message.getGroup_id(), message1);

                    }
                }
            }
        }
    }

}
