package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeSizes = (int[]) fromClient.readObject();

            // Check which algorithm to use to generate the maze.
            Configurations conf = Configurations.getInstance();
            String generateAlgo =  conf.getMazeGeneratingAlgorithm();
            IMazeGenerator mazeGenerator = null;

            if(generateAlgo.equalsIgnoreCase("empty")){
                mazeGenerator = new EmptyMazeGenerator();
            }
            else if(generateAlgo.equalsIgnoreCase("simple")){
                mazeGenerator = new SimpleMazeGenerator();
            }
            else if(generateAlgo.equalsIgnoreCase("DepthFirstSearch")){
                mazeGenerator = new MyMazeGenerator();
            }

            Maze maze = mazeGenerator.generate(mazeSizes[0], mazeSizes[1]);

            byte[] uncompressedMaze = maze.toByteArray();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(out);
            //SimpleCompressorOutputStream compressor = new SimpleCompressorOutputStream(out);
            compressor.write(uncompressedMaze);
            compressor.flush();
            toClient.writeObject(out.toByteArray());

            toClient.flush();
            fromClient.close();
            toClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}