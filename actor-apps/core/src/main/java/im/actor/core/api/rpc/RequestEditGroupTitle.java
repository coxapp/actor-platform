package im.actor.core.api.rpc;
/*
 *  Generated by the Actor API Scheme generator.  DO NOT EDIT!
 */

import im.actor.runtime.bser.*;
import im.actor.runtime.collections.*;
import static im.actor.runtime.bser.Utils.*;
import im.actor.core.network.parser.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.google.j2objc.annotations.ObjectiveCName;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import im.actor.core.api.*;

public class RequestEditGroupTitle extends Request<ResponseSeqDate> {

    public static final int HEADER = 0x55;
    public static RequestEditGroupTitle fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestEditGroupTitle(), data);
    }

    private ApiGroupOutPeer groupPeer;
    private long rid;
    private String title;

    public RequestEditGroupTitle(@NotNull ApiGroupOutPeer groupPeer, long rid, @NotNull String title) {
        this.groupPeer = groupPeer;
        this.rid = rid;
        this.title = title;
    }

    public RequestEditGroupTitle() {

    }

    @NotNull
    public ApiGroupOutPeer getGroupPeer() {
        return this.groupPeer;
    }

    public long getRid() {
        return this.rid;
    }

    @NotNull
    public String getTitle() {
        return this.title;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.groupPeer = values.getObj(1, new ApiGroupOutPeer());
        this.rid = values.getLong(4);
        this.title = values.getString(3);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        if (this.groupPeer == null) {
            throw new IOException();
        }
        writer.writeObject(1, this.groupPeer);
        writer.writeLong(4, this.rid);
        if (this.title == null) {
            throw new IOException();
        }
        writer.writeString(3, this.title);
    }

    @Override
    public String toString() {
        String res = "rpc EditGroupTitle{";
        res += "groupPeer=" + this.groupPeer;
        res += ", rid=" + this.rid;
        res += ", title=" + this.title;
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}
