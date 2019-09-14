package top.guoziyang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.guoziyang.beans.Coord2d;
import top.guoziyang.enums.DisplayDataType;
import top.guoziyang.enums.EdgeWeightFormat;
import top.guoziyang.enums.EdgeWeightType;
import top.guoziyang.problems.TSPProblem;
import top.guoziyang.problems.impl.ExplicitDataTsp;
import top.guoziyang.problems.impl.ExplicitTsp;
import top.guoziyang.problems.impl.Simple2dTsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

    private static String name;
    private static int dimension;
    private static EdgeWeightType edgeWeightType;
    private static EdgeWeightFormat edgeWeightFormat;
    private static DisplayDataType displayDataType;

    private static Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static TSPProblem readConfig(String fileName) {
        TSPProblem problem = null;
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
                    edgeWeightType = EdgeWeightType.valueOf(line.split(":")[1].trim());
                    problem = handleEdgeWeightType(reader);
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Error happened when reading config file!", e);
        }
        return problem;
    }

    private static TSPProblem handleEdgeWeightType(BufferedReader reader) throws IOException {
        if(edgeWeightType == EdgeWeightType.EUC_2D || edgeWeightType == EdgeWeightType.ATT
                || edgeWeightType == EdgeWeightType.GEO) {
            return handle2dTsp(reader);
        } else if(edgeWeightType == EdgeWeightType.EXPLICIT) {
            return handleExplicitTsp(reader);
        } else {
            logger.error("Unsupported EDGE_WEIGHT_TYPE!");
            return null;
        }
    }

    private static TSPProblem handle2dTsp(BufferedReader reader) throws IOException {
        String line;
        while (!"NODE_COORD_SECTION".equals(line = reader.readLine())) {
            ;
        }
        List<Coord2d> nodeCoordSection = readCoordSection(reader);
        return Simple2dTsp.builder()
                .nodeCoordSection(nodeCoordSection)
                .name(name)
                .dimension(dimension)
                .edgeWeightType(edgeWeightType)
                .build();
    }

    private static TSPProblem handleExplicitTsp(BufferedReader reader) throws IOException {
        String line;
        while((line = reader.readLine()) != null) {
            line = line.trim();
            if(line.startsWith("EDGE_WEIGHT_FORMAT")) {
                edgeWeightFormat = EdgeWeightFormat.valueOf(line.split(":")[1].trim());
            } else if(line.startsWith("DISPLAY_DATA_TYPE")) {
                displayDataType = DisplayDataType.valueOf(line.split(":")[1].trim());
                if(displayDataType != DisplayDataType.NO_DISPLAY) {
                    return handleExplicitDataTsp(reader);
                }
                //TODO
            } else if("EDGE_WEIGHT_SECTION".equals(line)) {
                List<Integer> tempList = new ArrayList<>();
                while(!"EOF".equals(line = reader.readLine())) {
                    line = line.trim();
                    String[] splits = line.split("\\s+");
                    for(String split : splits) {
                        tempList.add(Integer.parseInt(split));
                    }
                }
                int[] edgeWeightSection = tempList.stream().mapToInt(Integer::valueOf).toArray();
                return ExplicitTsp.builder()
                        .edgeWeightFormat(edgeWeightFormat)
                        .edgeWeightSection(edgeWeightSection)
                        .name(name)
                        .dimension(dimension)
                        .edgeWeightType(edgeWeightType)
                        .build();
            }
        }
        logger.error("Unexpected error!");
        return null;
    }

    private static TSPProblem handleExplicitDataTsp(BufferedReader reader) throws IOException {
        String line;
        while(!"EDGE_WEIGHT_SECTION".equals(line = reader.readLine())) {
            ;
        }
        List<Integer> tempList = new ArrayList<>();
        while(!"DISPLAY_DATA_SECTION".equals(line = reader.readLine())) {
            line = line.trim();
            String[] splits = line.split("\\s+");
            for(String split : splits) {
                tempList.add(Integer.parseInt(split));
            }
        }
        int[] edgeWeightSection = tempList.stream().mapToInt(Integer::valueOf).toArray();
        List<Coord2d> displayDataSection = readCoordSection(reader);
        return ExplicitDataTsp.builder()
                .displayDataType(displayDataType)
                .displayDataSection(displayDataSection)
                .edgeWeightFormat(edgeWeightFormat)
                .edgeWeightSection(edgeWeightSection)
                .name(name)
                .dimension(dimension)
                .edgeWeightType(edgeWeightType)
                .build();
    }

    private static List<Coord2d> readCoordSection(BufferedReader reader) throws IOException {
        String line;
        List<Coord2d> nodeCoordSection = new ArrayList<>();
        while(!"EOF".equals(line = reader.readLine())) {
            line = line.trim();
            String[] splits = line.split("\\s+");
            Coord2d coord = new Coord2d(Integer.parseInt(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[2]));
            nodeCoordSection.add(coord);
        }
        return nodeCoordSection;
    }

}
