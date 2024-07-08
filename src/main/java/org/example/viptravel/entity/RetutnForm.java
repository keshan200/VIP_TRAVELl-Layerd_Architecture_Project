package org.example.viptravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data


public class RetutnForm {

    private org.example.viptravel.entity.Return Return;
    private List<ReturnDetails> returnList;
}
