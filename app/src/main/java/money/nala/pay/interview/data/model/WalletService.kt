package money.nala.pay.interview.data.model

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import money.nala.pay.interview.R
import money.nala.pay.interview.NalaApp

enum class WalletService(
    val id: Int,
    val serviceName: String,
    val operatorName: String,
    val mobileCountryCode: String,
    val mobileNetworkCode: String,
    val serviceCountry: WalletServiceCountry,
    val serviceCurrency: WalletServiceCurrency,
    val serviceType: WalletServiceType,
    val canTransactWith: Boolean,
    val homeLogo: Int,
    val historyLogo: Int,
    val simLogo: Int,
    val colorResource: Int
) {
    TZ_VODA(13, WalletService.TZ_VODA_SERVICE, WalletService.TZ_VODA_NAME,
        WalletService.MCC_TZ, "04", WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.MNO, true, R.drawable.ic_home_voda, R.drawable.ic_history_voda,
        R.drawable.ic_sim_voda, R.color.vodacom),

    TZ_TIGO(6, WalletService.TZ_TIGO_SERVICE, WalletService.TZ_TIGO_NAME,
        WalletService.MCC_TZ, "02", WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.MNO, true, R.drawable.ic_home_tigo, R.drawable.ic_history_tigo,
        R.drawable.ic_sim_tigo, R.color.tigopesa),

    TZ_AIRTEL(16, WalletService.TZ_AIRTEL_SERVICE, WalletService.TZ_AIRTEL_NAME,
        WalletService.MCC_TZ, "05", WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.MNO, true, R.drawable.ic_home_airtel, R.drawable.ic_history_airtel,
        R.drawable.ic_sim_airtel, R.color.airtel),

    TZ_HALO(35, WalletService.TZ_HALO_SERVICE, WalletService.TZ_HALO_NAME,
        WalletService.MCC_TZ, "09", WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.MNO, true, R.drawable.ic_home_halotel, R.drawable.ic_history_halotel,
        R.drawable.ic_sim_halotel, R.color.halotel),

    TZ_ZANTEL(40, WalletService.TZ_ZANTEL_SERVICE, WalletService.TZ_ZANTEL_NAME,
        WalletService.MCC_TZ, "03", WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.MNO, true, R.drawable.ic_home_zantel, R.drawable.ic_history_zantel,
        R.drawable.ic_sim_zantel, R.color.zantel),

    TZ_TTCL(41, WalletService.TZ_TTCL_SERVICE, WalletService.TZ_TTCL_NAME,
        WalletService.MCC_TZ, "07", WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.MNO, true, R.drawable.ic_home_ttcl, R.drawable.ic_history_ttcl,
        R.drawable.ic_sim_ttcl, R.color.ttcl),

    UG_MTN(42, WalletService.UG_MTN_SERVICE, WalletService.UG_MTN_NAME,
        WalletService.MCC_UG, "10", WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.MNO, true, R.drawable.ic_home_mtn, R.drawable.ic_history_mtn,
        R.drawable.ic_sim_mtn, R.color.mtn),

    UG_AIRTEL(43, WalletService.UG_AIRTEL_SERVICE, WalletService.UG_AIRTEL_NAME,
        WalletService.MCC_UG, "01", WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.MNO, true, R.drawable.ic_home_airtel, R.drawable.ic_history_airtel,
        R.drawable.ic_sim_airtel, R.color.airtel),

    UG_AFRICELL(44, WalletService.UG_AFRICELL_SERVICE, WalletService.UG_AFRICELL_NAME,
        WalletService.MCC_UG, "14", WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.MNO, true, R.drawable.ic_home_africell, R.drawable.ic_history_africell,
        R.drawable.ic_sim_africell, R.color.africell),

    UG_STANBIC(45, WalletService.TZ_STANBIC_SERVICE, WalletService.TZ_STANBIC_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.BANK, true, R.drawable.ic_home_stanbic, R.drawable.ic_history_stanbic,
        R.drawable.ic_sim_stanbic, R.color.stanbic),

    UG_CENTENARY(46, WalletService.UG_CENTENARY_SERVICE, WalletService.UG_CENTENARY_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.BANK, true, R.drawable.ic_home_centenary, R.drawable.ic_history_centenary,
        R.drawable.ic_sim_centenary, R.color.centenary),

    UG_DFCU(47, WalletService.UG_DFCU_SERVICE, WalletService.UG_DFCU_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.BANK, true, R.drawable.ic_home_dfcu, R.drawable.ic_history_dfcu,
        R.drawable.ic_sim_dfcu, R.color.dfcu),

    UG_EQUITY(48, WalletService.UG_EQUITY_SERVICE, WalletService.UG_EQUITY_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.UG, WalletServiceCurrency.UGX,
        WalletServiceType.BANK, true, R.drawable.ic_home_equity, R.drawable.ic_history_equity,
        R.drawable.ic_sim_equity, R.color.equity),

    TZ_CRDB(5, WalletService.TZ_CRDB_SERVICE, WalletService.TZ_CRDB_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, true, R.drawable.ic_home_crdb, R.drawable.ic_history_crdb,
        R.drawable.ic_sim_crdb, R.color.crdb),

    TZ_NMB(7, WalletService.TZ_NMB_SERVICE, WalletService.TZ_NMB_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, true, R.drawable.ic_home_nmb, R.drawable.ic_history_nmb,
        R.drawable.ic_sim_nmb, R.color.nmb),

    TZ_NBC(8, WalletService.TZ_NBC_SERVICE, WalletService.TZ_NBC_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, true, R.drawable.ic_home_nbc, R.drawable.ic_history_nbc,
        R.drawable.ic_sim_nbc, R.color.nbc),

    TZ_STANBIC(9, WalletService.TZ_STANBIC_SERVICE, WalletService.TZ_STANBIC_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, false, R.drawable.ic_home_stanbic, R.drawable.ic_history_stanbic,
        R.drawable.ic_sim_stanbic, R.color.stanbic),

    TZ_EXIM(10, WalletService.TZ_EXIM_SERVICE, WalletService.TZ_EXIM_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, true, R.drawable.ic_home_exim, R.drawable.ic_history_exim,
        R.drawable.ic_sim_exim, R.color.exim),

    TZ_STANDARD_CHATTERED(11, WalletService.TZ_STANDARD_CHATTERED_SERVICE, WalletService.TZ_STANDARD_CHATTERED_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, true, R.drawable.ic_home_standard_chattered, R.drawable.ic_history_standard_chattered,
        R.drawable.ic_sim_standard_chattered, R.color.standard_chattered),

    TZ_EQUITY(12, WalletService.TZ_EQUITY_SERVICE, WalletService.TZ_EQUITY_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, false, R.drawable.ic_home_equity, R.drawable.ic_history_equity,
        R.drawable.ic_sim_equity, R.color.equity),

    TZ_DTB(14, WalletService.TZ_DTB_SERVICE, WalletService.TZ_DTB_NAME,
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.TZ, WalletServiceCurrency.TZS,
        WalletServiceType.BANK, false, R.drawable.ic_home_dtb, R.drawable.ic_history_dtb,
        R.drawable.ic_sim_dtb, R.color.dtb),

    EMPTY(0, "empty", "empty",
        WalletService.MCC_EMPTY, WalletService.MNC_EMPTY, WalletServiceCountry.EMPTY, WalletServiceCurrency.EMPTY,
        WalletServiceType.EMPTY, false, R.drawable.ic_invalid,
        R.drawable.ic_invalid,
        R.drawable.ic_invalid,
        R.color.white);

    fun isEmpty() = this == EMPTY
    fun isNotEmpty() = this != EMPTY

    fun isUgandan() = this.serviceCountry == WalletServiceCountry.UG
    fun isTanzanian() = this.serviceCountry == WalletServiceCountry.TZ

    fun isMno() = this.serviceType == WalletServiceType.MNO
    fun isBank() = this.serviceType == WalletServiceType.BANK

    fun getHomeLogoDrawable(context: Context = NalaApp.appContext) =
        AppCompatResources.getDrawable(context, homeLogo)

    fun getHistoryLogoDrawable(context: Context = NalaApp.appContext) =
        AppCompatResources.getDrawable(context, historyLogo)

    fun getSimLogoDrawable(context: Context = NalaApp.appContext) =
        AppCompatResources.getDrawable(context, simLogo)

    companion object {
        fun from(id: Int): WalletService = values().firstOrNull { it.id == id } ?: EMPTY

        // Used for MNO names. Also in string resource files.
        private const val TZ_VODA_NAME = "Voda"
        private const val TZ_TIGO_NAME = "Tigo"
        private const val TZ_AIRTEL_NAME = "Airtel"
        private const val TZ_HALO_NAME = "Halotel"
        private const val TZ_ZANTEL_NAME = "Zantel"
        private const val TZ_TTCL_NAME = "TTCL"
        private const val UG_MTN_NAME = "MTN"
        private const val UG_AIRTEL_NAME = "Airtel"
        private const val UG_AFRICELL_NAME = "Africell"

        // Used for Bank Names
        private const val TZ_CRDB_NAME = "CRDB"
        private const val TZ_NMB_NAME = "NMB"
        private const val TZ_NBC_NAME = "NBC"
        private const val TZ_EXIM_NAME = "EXIM"
        private const val TZ_STANBIC_NAME = "Stanbic"
        private const val TZ_STANDARD_CHATTERED_NAME = "Standard Chattered"
        private const val TZ_DTB_NAME = "DTB"
        private const val UG_CENTENARY_NAME = "Centenary"
        private const val UG_DFCU_NAME = "DFCU"
        private const val UG_EQUITY_NAME = "Equity"
        private const val TZ_EQUITY_NAME = "Equity"

        // Used for names of Mobile Money services. Also in string resource files.
        private const val TZ_VODA_SERVICE = "Voda"
        private const val TZ_TIGO_SERVICE = "Tigo Pesa"
        private const val TZ_AIRTEL_SERVICE = "Airtel Money"
        private const val TZ_HALO_SERVICE = "HaloPesa"
        private const val TZ_ZANTEL_SERVICE = "Ezy Pesa"
        private const val TZ_TTCL_SERVICE = "TTCL PESA"
        private const val UG_MTN_SERVICE = "MTN Mobile Money"
        private const val UG_AIRTEL_SERVICE = "Airtel Money"
        private const val UG_AFRICELL_SERVICE = "Africell Money"
        // Used for Bank Services
        private const val TZ_CRDB_SERVICE = "CRDB"
        private const val TZ_NMB_SERVICE = "NMB"
        private const val TZ_EXIM_SERVICE = "EXIM"
        private const val TZ_STANBIC_SERVICE = "Stanbic"
        private const val TZ_STANDARD_CHATTERED_SERVICE = "Standard Chattered"
        private const val TZ_EQUITY_SERVICE = "Equity"
        private const val TZ_NBC_SERVICE = "NBC"
        private const val UG_CENTENARY_SERVICE = "Centenary"
        private const val UG_DFCU_SERVICE = "DFCU"
        private const val UG_EQUITY_SERVICE = "Equity"

        private const val TZ_DTB_SERVICE = "DTB"

        // SimInfo Extensions
        // MCC for Supported Countries.
        private const val MCC_TZ = "640"
        private const val MCC_UG = "641"

        // Empty MCC for Bank Wallets
        private const val MCC_EMPTY = "-1"
        private const val MNC_EMPTY = "-1"
    }
}