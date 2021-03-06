/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tools.soql;

import org.mule.tools.soql.query.SOQLData;
import org.mule.tools.soql.query.SOQLQuery;
import org.mule.tools.soql.query.SOQLSubQuery;
import org.mule.tools.soql.query.clause.*;
import org.mule.tools.soql.query.condition.FieldBasedCondition;
import org.mule.tools.soql.query.condition.LikeBasedCondition;
import org.mule.tools.soql.query.condition.SetBasedCondition;
import org.mule.tools.soql.query.condition.SetValues;
import org.mule.tools.soql.query.condition.operator.AndOperator;
import org.mule.tools.soql.query.condition.operator.NotOperator;
import org.mule.tools.soql.query.condition.operator.OrOperator;
import org.mule.tools.soql.query.condition.operator.Parenthesis;
import org.mule.tools.soql.query.data.Field;
import org.mule.tools.soql.query.data.FunctionCall;
import org.mule.tools.soql.query.data.Literal;
import org.mule.tools.soql.query.from.ObjectSpec;
import org.mule.tools.soql.query.order.OrderBySpec;
import org.mule.tools.soql.query.select.FieldSpec;
import org.mule.tools.soql.query.select.FunctionCallSpec;
import org.mule.tools.soql.query.select.TypeOf;
import org.mule.tools.soql.query.with.DataCategorySpec;

/**
 * Visitor defined for the SOQL Data structure returned by the SOQL Parser Helper.
 * It contains a visit method for each node type of the structure.
 */
public interface SOQLDataVisitor<T> {

    T visit(SOQLData soqlData);

    T visitSOQLQuery(SOQLQuery soqlQuery);

    T visitSOQLSubQuery(SOQLSubQuery soqlSubQuery);

    //Clause

    T visitSelectClause(SelectClause selectClause);

    T visitFromClause(FromClause fromClause);

    T visitWithPlainClause(WithPlainClause withPlainClause);

    T visitWithDataCategoryClause(WithDataCategoryClause withDataCategoryClause);

    T visitWhereClause(WhereClause whereClause);

    T visitGroupByPlainClause(GroupByPlainClause groupByPlainClause);

    T visitGroupByCubeClause(GroupByCubeClause groupByCubeClause);

    T visitGroupByRollupClause(GroupByRollupClause groupByRollupClause);

    T visitHavingClause(HavingClause havingClause);

    T visitOrderByClause(OrderByClause orderByClause);

    T visitForClause(ForClause forClause);

    T visitUpdateClause(UpdateClause updateClause);

    //Select

    T visitFieldSpec(FieldSpec fieldSpec);

    T visitFunctionCallSpec(FunctionCallSpec functionCallSpec);

    T visitTypeOf(TypeOf typeOf);

    //With

    T visitDataCategorySpec(DataCategorySpec dataCategorySpec);

    //OrderBy

    T visitOrderBySpec(OrderBySpec orderBySpec);

    //From

    T visitObjectSpec(ObjectSpec objectSpec);

    //Data

    T visitField(Field field);

    T visitFunctionCall(FunctionCall functionCall);

    T visitLiteral(Literal literal);

    //Condition

    T visitSetBasedCondition(SetBasedCondition setBasedCondition);

    T visitLikeBasedCondition(LikeBasedCondition likeBasedCondition);

    T visitFieldBasedCondition(FieldBasedCondition fieldBasedCondition);

    T visitSetValues(SetValues setValues);

    T visitParenthesis(Parenthesis parenthesis);

    T visitOrOperator(OrOperator orOperator);

    T visitNorOperator(NotOperator notOperator);

    T visitAndOperator(AndOperator andOperator);

}
