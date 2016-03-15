package org.mule.soql.query.clause;

import org.mule.soql.SOQLDataVisitor;
import org.mule.soql.query.condition.Condition;

/**
 * Created by damianpelaez on 3/8/16.
 */
public class HavingClause extends ConditionClause {

    public HavingClause() {
    }

    public HavingClause(Condition condition) {
        super(condition);
    }

    protected String getConditionName() {
        return "HAVING";
    }

    public <T> T accept(SOQLDataVisitor<? extends T> soqlDataVisitor) {
        return soqlDataVisitor.visitHavingClause(this);
    }

}
