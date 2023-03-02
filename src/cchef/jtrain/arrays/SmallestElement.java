package cchef.jtrain.arrays;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
class SmallestElement {
    public static final String DEFAULT_ENCODING = "UTF-8";
    static class Input {

        /**
         * @param inputtt is just a basic string representation of int test cases for now.
         * @return byte[]
         * @throws UnsupportedEncodingException
         */
        public byte[] getInputAsByteArray(String inputtt) throws UnsupportedEncodingException {
            String input = "255241363-32-21-7";
            //From readRawRecord(Long bytePostion) seeks to rowbyteposition in dbfile
            // then does this    data = new byte[recordLength];
            //                   int length = this.dbFile.read(data);
            byte[] inputAsBytes = input.getBytes(DEFAULT_ENCODING);
            return null;

            //Why byte level
            //  1 practice
            //  2 all info in one string including schema info in front
            //String input0 = "HeaderLength/StringLength/TypeOfTestCase/NumberOfTestCases|255241363-32-21-7";
            // 1 encode to byte array
            // ____start__
            // int[] caseMap = {0, 0, 1, 6, 0, 7, 13};
            // System.out.println(caseMap);
            // return caseMap; //Template/Schema
            //ArrayList<Integer> inputMap = new
        }

        /**
         * Checks validity of test case String coding.
         *
         * @param testCases String
         * @return
         * @throws UnsupportedEncodingException
         */
        public String inputStringHandler(String testCases) throws IOException {
            // build string/ test validity designate testcase type
            byte[] testCase = getInputAsByteArray(testCases);
            DataInputStream stream = new DataInputStream(new ByteArrayInputStream(testCase)); return readInputStream(stream);
        }

        public String readInputStream(final DataInputStream stream) throws IOException {
            String value = null;
            int fieldLength = stream.readInt();
            byte[] bArray = new byte[fieldLength];
            int length = stream.read(bArray);
                value = new String(bArray, 0, length, DEFAULT_ENCODING);
            return value;


        }
    }

        public static void main(String[] args) {
            // int [] retrieved = Input.getCaseMap();
            //System.out.println(Arrays.toString(retrieved));

        }
    }
