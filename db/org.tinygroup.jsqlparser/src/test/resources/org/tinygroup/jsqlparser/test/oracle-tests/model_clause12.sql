--
--  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
--
--  Licensed under the GPL, Version 3.0 (the "License");
--  you may not use this file except in compliance with the License.
--  You may obtain a copy of the License at
--
--       http://www.gnu.org/licenses/gpl.html
--
--  Unless required by applicable law or agreed to in writing, software
--  distributed under the License is distributed on an "AS IS" BASIS,
--  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--  See the License for the specific language governing permissions and
--  limitations under the License.
--

select organisation,
level1, level2, level3, level4 from
( select 'org_name' as organisation, level as org_level, ename
from emp connect by prior empno = mgr
start with ename = 'king')
model
return updated rows
partition by
(organisation)
dimension by (rownum rn)
measures (lpad(' ',10) level1,
lpad(' ',10) level2, lpad(' ',10) level3,
lpad(' ',10) level4, org_level, ename)
rules update
( level1[any] = case when org_level[cv()] = 1 then ename [cv()] end,
level2[any] = case when org_level[cv()] = 2 then ename [cv()] end,
level3[any] = case when org_level[cv()] = 3 then ename [cv()] end,
level4[any] = case when org_level[cv()] = 4 then ename [cv()] end )
