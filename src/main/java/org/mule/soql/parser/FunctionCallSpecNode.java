package org.mule.soql.parser;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.mule.soql.parser.utils.SOQLCommonTreeUtils;
import org.mule.soql.query.FunctionCall;
import org.mule.soql.query.FunctionCallSpec;

/**
 * Created by damianpelaez on 2/23/16.
 */
public class FunctionCallSpecNode extends SOQLCommonTree {

    public FunctionCallSpecNode(int tokenType) {
        super(new CommonToken(tokenType, "FUNCTION_CALL_SPEC"));
    }

    @Override
    public FunctionCallSpec createSOQLData() {
        FunctionCallSpec functionCallSpec = new FunctionCallSpec();

        this.processFirstNode(functionCallSpec);
        this.processSecondNode(functionCallSpec);

        return functionCallSpec;
    }

    private void processFirstNode(FunctionCallSpec functionCallSpec) {
        CommonTree child = (CommonTree) this.getChild(0);

        this.createFunctionCall(child,functionCallSpec);
    }

    private void processSecondNode(FunctionCallSpec functionCallSpec) {
        CommonTree child = (CommonTree) this.getChild(1);

        if (child != null) {
            functionCallSpec.setAlias(child.getText());
        }
    }

    private void createFunctionCall(CommonTree node, FunctionCallSpec functionCallSpec) {
        if (!SOQLCommonTreeUtils.matchesType(node,SOQLParser.FUNCTION_CALL)) { return; }

        SOQLCommonTree soqlNode = (SOQLCommonTree) node;

        functionCallSpec.setFunctionCall((FunctionCall) soqlNode.createSOQLData());
    }

}