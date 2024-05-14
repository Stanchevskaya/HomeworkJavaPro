package homework6;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseEmployee {
    private String status;
    private List<Object> data;
    private String message;
}
