/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tools.soql.parser;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.mule.tools.soql.parser.utils.SOQLCommonTreeUtils;
import org.mule.tools.soql.query.with.DataCategorySelector;
import org.mule.tools.soql.query.with.DataCategorySpec;

import java.util.List;

/**
 * Created by damianpelaez on 2/23/16.
 */
public class DataCategorySpecNode extends SOQLCommonTree {

    public DataCategorySpecNode(int tokenType) {
        super(new CommonToken(tokenType, "DATA_CATEGORY_SPEC"));
    }

    @Override
    public DataCategorySpec createSOQLData() {
        DataCategorySpec dataCategorySpec = new DataCategorySpec();

        this.processFirstNode(dataCategorySpec);
        this.processSecondNode(dataCategorySpec);
        this.processThirdNode(dataCategorySpec);

        return dataCategorySpec;
    }

    private void processFirstNode(DataCategorySpec dataCategorySpec) {
        CommonTree node = (CommonTree) this.getChild(0);

        if(node == null) { return; }

        dataCategorySpec.setDataCategoryGroupName(node.getText());
    }

    private void processSecondNode(DataCategorySpec dataCategorySpec) {
        CommonTree node = (CommonTree) this.getChild(1);

        if(node == null) { return; }

        this.fillDataCategorySelector(node, dataCategorySpec);
    }

    private void processThirdNode(DataCategorySpec dataCategorySpec) {
        CommonTree node = (CommonTree) this.getChild(2);

        if(node == null) { return; }

        this.fillDataCategoryNames(node, dataCategorySpec);
    }

    private void fillDataCategorySelector(CommonTree node, DataCategorySpec dataCategorySpec) {
        DataCategorySelector dataCategorySelector = DataCategorySelector.get(node.getText());

        if(dataCategorySelector == null) { return; }

        dataCategorySpec.setDataCategorySelector(dataCategorySelector);
    }

    private void fillDataCategoryNames(CommonTree node, DataCategorySpec dataCategorySpec) {
        if(!SOQLCommonTreeUtils.matchesAnyType(node, SOQLParser.DATA_CATEGORY_PARAMETERS)) { return; }

        List<CommonTree> children = (List<CommonTree>) node.getChildren();

        for(CommonTree child : children) {
            dataCategorySpec.addDataCategoryName(child.getText());
        }
    }

}
