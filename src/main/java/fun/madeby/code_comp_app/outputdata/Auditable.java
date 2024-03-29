package fun.madeby.code_comp_app.outputdata;

import fun.madeby.code_comp_app.datatypes.outputtypes.DTOutput;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Only Auditable from perspective of it includes original data for now. Creation not controlled, all manner of data
 * could be constructed with the timestamp.
 */
public class Auditable implements DataSnapshot {
  private final String UTC_OFFSET;
  private final ZonedDateTime CREATED;
  private final DTOutput DTT_OUTPUT;
  private final String[][] SNAPSHOT_ORIGINAL_DATA;

  public Auditable(final DTOutput DTT_OUTPUT, final String[][] SNAPSHOT_ORIGINAL_DATA) {
    this.UTC_OFFSET =
        "UTC+2"; // method for dynamic but then...using vpn, would have to be localized to one
                 // region. method..
    this.CREATED = Instant.now().atZone(ZoneId.of(UTC_OFFSET)); // if offset were dynamic
    this.DTT_OUTPUT = DTT_OUTPUT;
    this.SNAPSHOT_ORIGINAL_DATA = SNAPSHOT_ORIGINAL_DATA;
  }

  public String getUTC_OFFSET() {
    return UTC_OFFSET;
  }

/**
* 
   * @return
*/
@Override
public DTOutput getDT_OUTPUT() {
  return DTT_OUTPUT;
}

public ZonedDateTime getCREATED() {
    return CREATED;
  }

  // possibility for enum of reports available for DTOutput.

  public String[][] getSNAPSHOT_ORIGINAL_DATA() {
    return SNAPSHOT_ORIGINAL_DATA;
  }
}
