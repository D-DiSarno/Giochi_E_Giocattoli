package controller.Http;

public enum Operator {
    GT,LT,EQ,NE,GE,LE,MATCH,QM;//Operatori compatibili con MySql

    public String toString() {
        switch (this) {
            case GT:
                return ">";
            case LT:
                return "<";
            case EQ:
                return "=";
            case NE:
                return "!=";
            case GE:
                return ">=";
            case LE:
                return "<=";
            case MATCH:
                return "LIKE";
            case QM:
                return "?";
            default:
                throw new IllegalArgumentException();
        }
    }
}
