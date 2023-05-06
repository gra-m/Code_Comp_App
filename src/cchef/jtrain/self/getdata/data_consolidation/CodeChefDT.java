package cchef.jtrain.self.getdata.data_consolidation;

import java.util.Arrays;
import java.util.List;

public class CodeChefDT implements DataType {
private final long CASE_INDEX;
private final long CASE_ID;
private final GenericCaseType CASE_TYPE;
private final String[] CASE_INPUT_DATA;
private boolean status_passing;
// todo make write once
private boolean dataWritten;
private String [] EXP_OUTPUT_DATA;
private String [] ACT_OUTPUT_DATA;


public CodeChefDT(final long CASE_INDEX, final long CASE_ID, final GenericCaseType CASE_TYPE) {
      this.CASE_TYPE = CASE_TYPE;
      int linesPerOutput = this.CASE_TYPE.getLinesPerOutput();

      this.CASE_INDEX = CASE_INDEX;
      this.CASE_ID = CASE_ID;
      this.CASE_INPUT_DATA = new String[this.CASE_TYPE.getLinesPerInput()];
      this.EXP_OUTPUT_DATA = new String[linesPerOutput];
      this.ACT_OUTPUT_DATA = new String[linesPerOutput];

}



private CodeChefDT(final long CASE_INDEX, final long CASE_ID, final CaseType CASE_TYPE, final String[] CASE_INPUT_DATA, final String[] EXP_OUTPUT_DATA, final String[] ACT_OUTPUT_DATA, boolean status_passing) {
      this.CASE_INDEX = CASE_INDEX;
      this.CASE_ID = CASE_ID;
      this.CASE_TYPE = (GenericCaseType) CASE_TYPE;
      this.CASE_INPUT_DATA = CASE_INPUT_DATA;
      this.EXP_OUTPUT_DATA = EXP_OUTPUT_DATA;
      this.ACT_OUTPUT_DATA = ACT_OUTPUT_DATA;
      this.status_passing = status_passing;
      this.dataWritten = true;
}

/**
*
   * @return
*/
@Override
public String contentDescription() {
      return null;
}

/**
*
  */
@Override
public void writeCaseData(String[] InputExpectedActual) {


}

/**
*
   * @return
*/
@Override
public boolean ascertainStatus() {
      return false;
}

/**
*
   * @return
*/
@Override
public int getLinesPerInput() {
      return this.CASE_TYPE.getLinesPerInput();
}

/**
*
   * @return
*/
@Override
public int getLinesPerOutput() {
      return this.CASE_TYPE.getLinesPerOutput();
}

/**
*
   * @param input
   * @param expected
   * @param actual
   * @return
*/
@Override
public DataType[] consolidateData(String[] input, String[] expected, String[] actual) {
      int totalCases = (int) CASE_TYPE.getTotalCases();
      int inDatLength = CASE_TYPE.getLinesPerInput();
      int outDatLength = CASE_TYPE.getLinesPerOutput();
      int inputIndex = 0;
      int expIndex = 0;
      int actIndex = 0;
      DataType[] consolidatedData = new CodeChefDT[totalCases];

      for (int i = 0; i < totalCases; i++) {
            String[] inputData = new String[inDatLength];
            String[] expData = new String[outDatLength];
            String[] actData = new String[outDatLength];
            boolean expectedMatchesActual;

            for (int in = 0; in < inDatLength; in++)  {
                  inputData[in] = input[inputIndex];
                  inputIndex++;
            }

            for (int ex = 0; ex < outDatLength; ex++)  {
                  expData[ex] = expected[expIndex];
                  expIndex++;
            }

            for (int act = 0; act < outDatLength; act++)  {
                  actData[act] = actual[actIndex];
                  actIndex++;
            }

            expectedMatchesActual = Arrays.deepEquals(expData,actData);

            consolidatedData[i] = new CodeChefDT(i, (i+1), this.CASE_TYPE, inputData, expData, actData, expectedMatchesActual);

    }

      return consolidatedData;
}

/**
* 
   * @param input
   * @param expected
   * @param actual
   * @return
*/
@Override
public List<DataType> consolidateDataToList(String[] input, String[] expected, String[] actual) {
      return null;
}


@Override
public String toString() {
      return "CodeChefDT{" + "CASE_INDEX=" + CASE_INDEX + ", CASE_ID=" + CASE_ID + ", CASE_TYPE=" + CASE_TYPE + ", CASE_INPUT_DATA=" + Arrays.toString(CASE_INPUT_DATA) + ", status_passing=" + status_passing + ", dataWritten=" + dataWritten + ", EXP_OUTPUT_DATA=" + Arrays.toString(EXP_OUTPUT_DATA) + ", ACT_OUTPUT_DATA=" + Arrays.toString(ACT_OUTPUT_DATA) + '\'' + '}';
}
}
