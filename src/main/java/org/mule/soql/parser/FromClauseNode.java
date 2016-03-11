package org.mule.soql.parser;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.mule.soql.parser.utils.SOQLCommonTreeUtils;
import org.mule.soql.query.ObjectSpec;
import org.mule.soql.query.clause.FromClause;

import java.util.List;

/**
 * Created by damianpelaez on 3/4/16.
 */
public class FromClauseNode extends SOQLCommonTree {

    public FromClauseNode(Token t) {
        super(t);
    }

    @Override
    public FromClause createSOQLData() {
        FromClause fromClause = new FromClause();

        this.processChildren(fromClause);

        return fromClause;
    }

    private void processChildren(FromClause fromClause) {
        List<CommonTree> children = (List<CommonTree>) this.getChildren();

        if(children == null) { return; }

        for(CommonTree child : children) {
            this.fillObjectSpec(child, fromClause);
        }
    }

    private void fillObjectSpec(CommonTree node, FromClause fromClause) {
        if(!SOQLCommonTreeUtils.matchesAnyType(node, SOQLParser.OBJECT_SPEC)) { return; }

        SOQLCommonTree soqlNode = (SOQLCommonTree) node;

        if(node.getChildIndex() == 0) {
            fromClause.setMainObjectSpec((ObjectSpec) soqlNode.createSOQLData());
        } else {
            fromClause.addRelationObjectSpec((ObjectSpec) soqlNode.createSOQLData());
        }
    }

}
