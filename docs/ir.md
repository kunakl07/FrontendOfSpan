SPAN IR as expressed in SpecDFA
=================


This document details a regular expression based
approach to match a statement in an IR to capture
the relevant pieces following a specific pattern.


## List of symbols and their meanings:

    _       match anything
    v       a variable
    l       a literal
    e       an expression
    f       a function
    fp      a function pointer
    
    op      an operator
    op.a    an operator - aritmetic + - * / %
    op.r    an operator - relational < <= == != >= >
    op.b    an operator - bitwise & | ^ ~
    ==      the operator '=='
            all operators can be expressed literally


## Matching statement forms:
Note that type prefix like `'Num:'` is optional.

1. Numeric variable assignment: `'Num: v1 = v2'`
1. Numeric variable assignment: `'Num: v1 = l1'`
1. Relational expression assignment: `'v1 = v2 % 2'`
   --- here the type prefix 'Num:' is optional
1. Relational expression assignment: `'v1 = e1{v2 % 2}'`
   --- the rhs expression is captured with name e1
1. Array assignment: `'_[_] = v1'`
1. Array addressof: `'v1 = e2{&v2[e1]}'`
   --- the addressof expression is named e2.
1. Dereference expression: `'*v1 = v2'`
1. Dereference expression: `'v1 = *v2'`
1. Dereference expression: `'*v1 = l1'`

Here `v1`, `v2` etc. captures the variables at the given positions.
Similarly all the names capture the entity they may refer to.


## Overall usage
Available Expressions transfer function for selected instructions:

    Av.t {
    
      dfvin, 'Num: v1 = e1' {
        kill = {e  |  e <- allExpr,      v1 in opds(e)}
        gen  = {e1 |                  not v1 in opds(e1)}
        Set.union(Set.difference(dfvin, kill), gen)  
      }
    
      dfvin, 'Num: v1 = v2'{
        kill = {e | e <- allExpr, v1 in opds(e)}
        Set.difference(dfvin, kill)
      }
    
      dfvin, 'v1 = l1' {
        kill = {e | e <- allExpr, v1 in opds(e)}
        Set.difference(dfvin,, kill)
      }
    
      dfvin, '_' {
        dfvin
      }
    
    }



