package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; // Getter + Setter + ... 
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ActionForward {
  private String view;
  private boolean isRedirect;
}
