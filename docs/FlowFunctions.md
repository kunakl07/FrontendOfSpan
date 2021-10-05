### Intra live

```
gen(Node n)::avl{
}
gen(Node n) returns Avl{
    %stmt(n){
        Expr e                      => { Var v | v in opds(e) }
        Var v = Expr e              => { Var v1| v1 in opds(e) }
        Var v = Int e               => {}
        Var v = Var v1              => {v1}
        return  Var v               => {v}
        otherwise                   => {}
    %}
}
gen(Var v, =, Var v1)
opds(Expr e)returns Set(Var){
    Expr lopd Op op Expr ropd   => opds(lopd) + opds(ropd)
    Op op Expr ropd             => opds(ropd)
    Var v                       => {v}
    otherwise                   => {}
}
```

Temporary AST Structure

FlowFunctionDef (identifier, returnValue)
    List<Stmt>
        
    Stmt(List<ValueTypes/>, ReturnVal)
        * ValueType(String value, Stringtype) (eg - Expr a)/ FFStatementAssignmentNode(ValueType lhs, ValueType rhs)
        / FFReturnStatement(ValueType)
        / OtherwiseNode
        
    ReturnVal(int type - (0 - Set, 1- Map, 2- List, 3- SomethingElse), 
          * SuchThatNode / Tuple / ListNode
    SuchThatNode(ValueType, 
        * InNode (identifier, functionCallId)
                         