package org.example.viptravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data


public class RetutnFormDTO {

    private ReturnDTO Return;
    private List<ReturnDetailsDTO> returnList;
}
