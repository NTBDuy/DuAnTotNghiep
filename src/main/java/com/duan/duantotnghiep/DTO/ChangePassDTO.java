package com.duan.duantotnghiep.DTO;

import lombok.Data;

@Data
public class ChangePassDTO {
    String current_pass;
    String new_pass;
    String confirm_pass;
    String username;
}
