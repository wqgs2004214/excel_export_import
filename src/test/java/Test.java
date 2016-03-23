import zyx.importfile.ConfigParser;
import zyx.importfile.ConfigurationParserFactory;
import zyx.importfile.FileImportExecutor;
import zyx.importfile.domain.MapResult;
import zyx.importfile.domain.common.Configuration;
import zyx.importfile.exception.FileImportException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyuanxin on 2016/3/23.
 */
public class Test {
    private final static String xmlName = "uploadCommonProduct.xml";
    public static void main(String[] args) throws FileImportException, FileNotFoundException {
        ConfigParser configParser = ConfigurationParserFactory.getConfigParser(Configuration.ParserType.XML);
        Path configFile = Paths.get("/import/config.xml");
        File importFile = new File("/import/test.xlsx");
        Configuration configuration = null;
        try {
            configuration = configParser.getConfig(new FileInputStream("/import/config.xml"));
            MapResult mapResult = (MapResult) FileImportExecutor.importFile(configuration, importFile, importFile.getName());
            List<Map> maps = mapResult.getResult();
            for (Map<String, Object> map : maps) {
                int index = (int) map.get("index");
                float f1 = (float) map.get("float");
                String string = (String) map.get("string");
                Date date = (Date) map.get("date");
                BigDecimal bigDecimal = (BigDecimal) map.get("bigdecimal");
                System.out.println("index :" + index + " f1 : " + f1 + " string : " + string
                + " date : " + date.toString() + " bigdecimal " + bigDecimal);
            }
        } catch (FileImportException e) {
            System.out.println(e);;
        }
    }
}
