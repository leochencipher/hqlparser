import org.antlr.runtime.CommonTokenStream;

public class Test {

    public static void main(String[] args) throws Exception

    {
        ANTLRNoCaseFileStream ancf = new ANTLRNoCaseFileStream("hello.hql");

        HiveLexer lexer = new HiveLexer(ancf);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        HiveParser parser = new HiveParser(tokens);

        parser.selectStatement();
    }

}