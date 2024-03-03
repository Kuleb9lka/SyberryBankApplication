package SyberryBankApplication.Syberry.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum SyberryBanks {

    ALPHA_BANK ("Альфа-Банк"),
    BELARUS_BANK("БеларусБанк"),
    NATIONAL_BANK("Национальный Банк");

    private final String name;

    public static List<String> getAllBanks(){

        List<String> allBanks = new ArrayList<>();

        allBanks.add(ALPHA_BANK.getName());
        allBanks.add(BELARUS_BANK.getName());
        allBanks.add(NATIONAL_BANK.getName());

        return allBanks;
    }



}
