package homework6;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestEmployee {
    private String name;
    private String salary;
    private String age;
}

