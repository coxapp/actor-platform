package im.actor.model.api;
/*
 *  Generated by the Actor API Scheme generator.  DO NOT EDIT!
 */

import im.actor.model.droidkit.bser.Bser;
import im.actor.model.droidkit.bser.BserParser;
import im.actor.model.droidkit.bser.BserObject;
import im.actor.model.droidkit.bser.BserValues;
import im.actor.model.droidkit.bser.BserWriter;
import im.actor.model.droidkit.bser.DataInput;
import static im.actor.model.droidkit.bser.Utils.*;
import java.io.IOException;
import im.actor.model.network.parser.*;
import java.util.List;
import java.util.ArrayList;

public class EncryptedDocumentV1 extends EncryptedContentV1 {

    private String name;
    private String mimeType;
    private EncryptedFileLocationV1 fileLocation;
    private FastThumb fastThumb;
    private EncryptedDocumentV1Ex extension;

    public EncryptedDocumentV1(String name, String mimeType, EncryptedFileLocationV1 fileLocation, FastThumb fastThumb, EncryptedDocumentV1Ex extension) {
        this.name = name;
        this.mimeType = mimeType;
        this.fileLocation = fileLocation;
        this.fastThumb = fastThumb;
        this.extension = extension;
    }

    public EncryptedDocumentV1() {

    }

    public int getHeader() {
        return 2;
    }

    public String getName() {
        return this.name;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public EncryptedFileLocationV1 getFileLocation() {
        return this.fileLocation;
    }

    public FastThumb getFastThumb() {
        return this.fastThumb;
    }

    public EncryptedDocumentV1Ex getExtension() {
        return this.extension;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.name = values.getString(1);
        this.mimeType = values.getString(2);
        this.fileLocation = values.getObj(3, new EncryptedFileLocationV1());
        this.fastThumb = values.optObj(4, new FastThumb());
        if (values.optBytes(6) != null) {
            this.extension = EncryptedDocumentV1Ex.fromBytes(values.getInt(5), values.getBytes(6));
        }
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        if (this.name == null) {
            throw new IOException();
        }
        writer.writeString(1, this.name);
        if (this.mimeType == null) {
            throw new IOException();
        }
        writer.writeString(2, this.mimeType);
        if (this.fileLocation == null) {
            throw new IOException();
        }
        writer.writeObject(3, this.fileLocation);
        if (this.fastThumb != null) {
            writer.writeObject(4, this.fastThumb);
        }
        if (this.extension != null) {
            writer.writeInt(5, this.extension.getHeader());
            writer.writeBytes(6, this.extension.toByteArray());
        }
    }

    @Override
    public String toString() {
        String res = "struct EncryptedDocumentV1{";
        res += "}";
        return res;
    }

}
