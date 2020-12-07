package mhj.Grp10_AppProject.WebAPI;

import mhj.Grp10_AppProject.Model.ExchangeRates;

//Idea inspired from https://stackoverflow.com/questions/3398363/how-to-define-callbacks-in-android
public interface APICallback {
    void OnApiCallback(ExchangeRates exchangeRates);
}
