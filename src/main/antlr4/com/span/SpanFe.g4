grammar SpanFe;
parse: statement*;


statement: varDecl
        | assignment
        | transferFunctionDef ;

varDecl: vdIdList+=Identifier (',' vdIdList+=Identifier)* IsA vdType=(Lattice|Set|List);


assignment:
        assignmentLhs=Identifier '.' latticeProperty=(Top| Bot| Meet| BI| PO)
            '=' assignmentRhs=(Identifier| SetSubset| SetSuperset) #latticePropertyAssignment
        |assignmentLhs=Identifier '.' latticeProperty=(Top| Bot| Meet| BI| PO)
                     '=' functionCall                               #latticePropertyAssignment

        | assignmentLhs=Identifier Assign componentLatticeCtorDef   #componentLatticeAssignment

        | assignmentLhs=Identifier Assign functionCall              #overallLatticeAssignment
        | assignmentLhs=Identifier Assign expression                #expressionAssignment
        ;

componentLatticeCtorDef: Identifier
            | Identifier '|' componentLatticeCtorDef
            | Identifier componentLatticeCtorDefValueType=(Num| Int| Float| Ptr)
            | Identifier componentLatticeCtorDefValueType=(Num| Int| Float| Ptr) '|' componentLatticeCtorDef;

functionCall: functionCallIdentifier=(Set| Map| Tuple| Identifier| SetIntersectCall| SetSubsetCall| SetUnion| SetDifference) LParen argsList RParen
            | Identifier '.' latticeProperty=(Meet| PO) LParen argsList RParen
            ;

argsList: Identifier (',' argsList)*
    | functionCall (',' argsList)*
    | valueTypesPlaceholder (',' argsList)*
    | entityTypesPlaceholder (',' argsList)*
    | containerTypesPlaceholder (',' argsList)*
    | Identifier'.'latticeProperty=(Top|Bot) (',' argsList)*
    | dfvVar=(Dfv|DfvIn|DfvOut) (',' argsList)*
;

transferFunctionDef: Identifier '.t' '{' transferFunctionBody+ '}' ;

transferFunctionBody: dataFlowVar=(Dfv|DfvIn|DfvOut) ',' IsA '{' assignment* expression+ '}';

expression: setComprehension
           | conditionalExpression
           | iterableExpression
           | functionCall
           ;

iterableExpression : Identifier MapOp allTypeValue=(AllExpr| AllVar| AllBinOpExpr| AllUnOpExpr| AllNumVar)
                    | Identifier MapOp functionCall ;

conditionalExpression:
                (Not)? lhs=Identifier relationalOperator=(LT|GT|LTE|GTE|EQ|NEQ|In) rhs=Identifier
                |(Not)? lhs=Identifier relationalOperator=(LT|GT|LTE|GTE|EQ|NEQ|In) functionCall
                ;

setComprehension:  '{' Identifier '|' (iterableExpression',')? conditionalExpression '}';

valueTypesPlaceholder: valueTypeValue=(Num| Int| Float| Ptr);
entityTypesPlaceholder: entityTypeValue=(Expr| Var| BinOpExpr| UnOpExpr);
containerTypesPlaceholder: containerTypeValue=(Set| Map| Tuple);
/**
* TOKENS
**/
IsA: 'isa';
Let: 'let';
// base constructible types
Lattice: 'Lattice';
Set: 'Set';
List: 'List';

// value types
Num: 'Num';
Int: 'Int';
Float: 'Float';
Ptr: 'Ptr';

// entity types
Expr: 'Expr';
Var: 'Var';
BinOpExpr: 'BinOpExpr';
UnOpExpr: 'UnOpExpr';

// all types
AllExpr: 'allExpr';
AllVar: 'allVar';
AllBinOpExpr: 'allBinOpExpr';
AllUnOpExpr: 'allUnOpExpr';
AllNumVar: 'allNumVar';

// container types

Map: 'Map';
Tuple: 'Tuple';

// inbuilt functions
SetSubsetCall: 'Set.subset';
SetIntersectCall: 'Set.intersect';
SetUnion: 'Set.union';
SetDifference: 'Set.difference';

// Booleans
True: 'True';
False: 'False';

// LATTICE properties
PO: 'po'; // partial order of this LATTICE
Top: 'top'; // top of this LATTICE
Bot: 'bot'; // bot of this LATTICE
Meet: 'meet'; // meet of this LATTICE
BI: 'bi'; // boundary information

// partial order macros
SetSubset:'#SetSubset';
SetSuperset:'#SetSupset';

// SPAN IR Instructions
AssignI: 'AssignI';
UseI:   'UseI';

// relational utils
MapOp: '<-';
LTE: '<=';
GTE: '>=';
LT: '<';
GT: '>';
EQ: '==';
NEQ: '!=';
Not: 'not';
In:   'in';         // translates in of python
Otherwise: 'otherwise';

// dfv vars
Dfv: 'dfv';
DfvIn: 'dfvin';
DfvOut: 'dfvout';

// others
Identifier: [a-zA-Z_]+[a-zA-Z0-9_]*;

SuchThat:'|';
LParen: '(';
RParen: ')';

LBrace:'{';
RBrace:'}';

SBOpen: '[';
SBClose: ']';

Assign: '=';

SimpleIrString1: '\'Num: v1 = e1\'';
SimpleIrString2: '\'v1 = l1\'';

Comment
 : '%' ~[\r\n]* -> skip
 ;

Space
 : [ \t\r\n] -> skip
 ;

V: [^v] ;

Other
 : . 
 ;