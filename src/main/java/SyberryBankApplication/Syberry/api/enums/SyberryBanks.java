package SyberryBankApplication.Syberry.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum SyberryBanks {

    ALPHA_BANK ("Альфа-Банк"),
    BELARUS_BANK("БеларусБанк"),
    NATIONAL_BANK("Национальный Банк");

    private final String name;

    private static List<String> allBanks;

    public static List<String> getAllBanks(){

        allBanks.add(String.valueOf(ALPHA_BANK));
        allBanks.add(String.valueOf(BELARUS_BANK));
        allBanks.add(String.valueOf(NATIONAL_BANK));

        return allBanks;
    }



}
