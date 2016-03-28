package org.mule.soql.query.from;

import org.mule.soql.SOQLDataVisitor;
import org.mule.soql.query.SOQLAbstractData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damianpelaez on 3/6/16.
 */
public class ObjectSpec extends SOQLAbstractData {
    private List<String> objectPrefixNames = new ArrayList<String>();
    private String objectName;
    private String alias;

    public ObjectSpec() {
    }

    public ObjectSpec(String objectName, String alias) {
        this.objectName = objectName;
        this.alias = alias;
    }

    @Override
    public String toSOQLText() {
        StringBuilder sb = new StringBuilder();

        if(objectPrefixNames != null) {
            for(String objectName : objectPrefixNames) {
                sb.append(objectName).append(".");
            }
        }

        if(objectName != null) {
            sb.append(objectName);
        }

        if(alias != null) {
            sb.append(" ").append(alias);
        }

        return sb.toString();
    }

    public <T> T accept(SOQLDataVisitor<? extends T> soqlDataVisitor) {
        return soqlDataVisitor.visitObjectSpec(this);
    }

    public void addObjectPrefixName(String objectPrefixName) {
        if(objectPrefixName == null) { return; }

        if(objectPrefixNames == null) {
            objectPrefixNames = new ArrayList<String>();
        }

        objectPrefixNames.add(objectPrefixName);
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public List<String> getObjectPrefixNames() {
        return objectPrefixNames;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}