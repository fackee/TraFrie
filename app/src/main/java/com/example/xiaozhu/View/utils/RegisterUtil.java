package com.example.xiaozhu.View.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.xiaozhu.Model.Utills.MySharedreference;

import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Registration;

/**
 * Created by xiaozhu on 2016/8/6.
 */
public class RegisterUtil {
    private Context mContext;
    public RegisterUtil(Context mContext, XMPPConnection conn){
        this.mContext = mContext;
        Registration registration = new Registration();
        registration.setType(IQ.Type.SET);
        registration.setUsername(new MySharedreference(mContext).username);
        registration.setPassword("123456");
        registration.addAttribute("android", "geolo_createUser_android");
        //创建包过滤器
        PacketFilter filter = new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class));
        //创建包收集器
        PacketCollector collector = conn.createPacketCollector(filter);
        //发送包
        conn.sendPacket(registration);
        //获取返回信息
        IQ result = (IQ) collector.nextResult(SmackConfiguration.getPacketReplyTimeout());
        // 取消收集
        collector.cancel();
        //通过返回信息判断
        if (result == null) {
            Toast.makeText(mContext,"服务器异常" ,Toast.LENGTH_SHORT).show();
        } else if (result.getType() == IQ.Type.ERROR) {
            if (result.getError().toString().equalsIgnoreCase("conflict(409)")) {
                Toast.makeText(mContext,"注册失败，用户已存在" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "注册失败", Toast.LENGTH_SHORT).show();
            }
        } else if (result.getType() == IQ.Type.RESULT) {
            Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
        }
    }
}
