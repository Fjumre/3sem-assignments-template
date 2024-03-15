package app.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class TokenDTO {

    String token;
    String username;
}
