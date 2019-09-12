package top.guoziyang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.guoziyang.enums.EdgeWeightType;
import top.guoziyang.problems.TSPProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {

    public static Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static TSPProblem readConfig(String fileName) {
        TSPProblem problem = null;
        String name;
        int dimension;
        EdgeWeightType edgeWeightType;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                line = line.trim();
                if("EOF".equals(line)) {
                    break;
                }
                if(line.startsWith("NAME")) {
                    name = line.split(":")[1].trim();
                } else if(line.startsWith("DIMENSION")) {
                    dimension = Integer.parseInt(line.split(":")[1].trim());
                } else if(line.startsWith("EDGE_WEIGHT_TYPE")) {
                    String edgeWeightTypeStr = line.split(":")[1].trim();
                    problem = handleEdgeWeightType(EdgeWeightType.valueOf(edgeWeightTypeStr));
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Error happened when reading config file!", e);
        }
        return problem;
    }

    private static TSPProblem handleEdgeWeightType(EdgeWeightType edgeWeightType) {
        return null;
    }

}
