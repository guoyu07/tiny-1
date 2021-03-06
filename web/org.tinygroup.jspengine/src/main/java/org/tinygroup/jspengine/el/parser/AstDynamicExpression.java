/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.jspengine.el.parser;

import javax.el.ELException;

import org.tinygroup.jspengine.el.lang.EvaluationContext;


/**
 * @author Jacob Hookom [jacob@hookom.net]
 * @version $Change: 181177 $$DateTime: 2001/06/26 08:45:09 $$Author: tcfujii $
 */
public final class AstDynamicExpression extends SimpleNode {
    public AstDynamicExpression(int id) {
        super(id);
    }

    public Class getType(EvaluationContext ctx)
            throws ELException {
        return this.children[0].getType(ctx);
    }

    public Object getValue(EvaluationContext ctx)
            throws ELException {
        return this.children[0].getValue(ctx);
    }

    public boolean isReadOnly(EvaluationContext ctx)
            throws ELException {
        return this.children[0].isReadOnly(ctx);
    }

    public void setValue(EvaluationContext ctx, Object value)
            throws ELException {
        this.children[0].setValue(ctx, value);
    }
}
