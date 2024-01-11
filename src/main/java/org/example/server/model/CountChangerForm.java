package org.example.server.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountChangerForm {
    private Cart cartCC;
    private CartItem item;

}
