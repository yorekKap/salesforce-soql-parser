package org.mule.soql.parser;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.mule.soql.parser.utils.SOQLCommonTreeUtils;
import org.mule.soql.query.Literal;
import org.mule.soql.query.condition.SetValues;

import java.util.List;

/**
 * Created by damianpelaez on 2/26/16.
 */
public class SetValuesNode extends SOQLCommonTree {

    public SetValuesNode(int tokenType) {
        super(new CommonToken(tokenType, "SET_VALUES"));
    }

    @Override
    public SetValues createSOQLData() {
        SetValues setValues = new SetValues();

        this.processChildren(setValues);

        return setValues;
    }

    private void processChildren(SetValues setValues) {
        List<CommonTree> children = (List<CommonTree>) this.getChildren();

        for(CommonTree child : children) {
            this.createLiteral(child, setValues);
        }
    }

    private void createLiteral(CommonTree node, SetValues setValues) {
        if (!SOQLCommonTreeUtils.matchesType(node, SOQLParser.LITERAL)) { return; }

        SOQLCommonTree soqlNode = (SOQLCommonTree) node;

        setValues.addValue((Literal) soqlNode.createSOQLData());
    }

}