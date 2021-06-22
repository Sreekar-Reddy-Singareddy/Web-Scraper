from pathy import file
from selenium import webdriver
from selenium.webdriver import Chrome
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium import *
from selenium.webdriver.support import expected_conditions as EC
import json

def scrape (url):
    try:
        driver = webdriver.Chrome(executable_path='/Users/sreekar/Downloads/chromedriver')
        specs_dict = {}
        driver.get(url)

        get_car_identification(driver, specs_dict)
        get_car_basic_details(driver, specs_dict)
        get_car_pricing(driver, specs_dict)
        get_car_location(driver, specs_dict)
        get_car_other_specs(driver, specs_dict)
        print(specs_dict)

        driver.quit()
        json_obj = json.dumps(specs_dict)

        return json_obj
    except:
        return "SCRAPE ERROR"



def get_car_identification(driver, json):
    car_id_element_xpath = "//div[@id='vehicle-title1-app-root']/" \
           "div/" \
           "ul[@class='additional-details list-inline mb-0 font-weight-normal ddc-font-size-small text-muted mt-3']"
    car_id_element = driver.find_element(By.XPATH, car_id_element_xpath)
    js_result = driver.execute_script(
        'element = arguments[0];'
        'arr = element.getElementsByTagName("li");'
        'vin = ""; stockId = ""; i = 0;'
        'if (arr.length > 2) i=1;'
        'vin = arr[i].textContent;'
        'stockId = arr[i+1].textContent;'
        'return [vin, stockId];', car_id_element)
    arr = list(js_result)
    json["vin"] = arr[0].split(":")[1].strip()
    json["stock_id"] = arr[1].split(":")[1].strip()


def get_car_basic_details(driver, json):
    car_element_xpath = "//div[@id='vehicle-title1-app-root']/" \
                           "div/" \
                           "h1[@class='vehicle-title m-0 line-height-reset']"
    car_element = driver.find_element(By.XPATH, car_element_xpath)
    js_result = driver.execute_script(
        'element = arguments[0];'
        'arr = element.getElementsByTagName("span");'
        'year_text = ""; trim_text = "";'
        'year_text = arr[0].textContent;'
        'trim_text = arr[1].textContent;'
        'return [year_text, trim_text];', car_element)
    arr = list(js_result)
    year = arr[0].strip().split(" ")[1]
    trim = arr[1].strip().split(" ")[1] # Todo: Need to change the logic to get complete trim.
    json["year"] = year
    json["trim"] = trim

    car_element_xpath = "//div[@id='quick-specs1-app-root']/" \
                        "dl[@class='line-height-condensed dl-horizontal row list-spaced']"
    car_info_element = driver.find_element(By.XPATH, car_element_xpath)
    js_result = driver.execute_script (
        'keyArr = arguments[0].getElementsByTagName("dt");'
        'valArr = arguments[0].getElementsByTagName("dd");'
        'len = keyArr.length;'
        'result = {};'
        'for (let i=0; i<len; i++) {'
        '  key = keyArr[i];'
        '  val = valArr[i];'
        '  keyText = "";'
        '  valText = "";'
        '  arr1 = key.getElementsByTagName("span");'
        '  arr2 = val.getElementsByTagName("span");'
        '  if (arr1.length == 0) keyText = key.textContent;'
        '  else keyText = arr1[arr1.length-1].textContent;'
        '  if (arr2.length == 0) valText = val.textContent;'
        '  else valText = arr2[arr2.length-1].textContent;'
        '  console.log(keyText);'
        '  result[keyText] = valText;'
        '}'
        'return result;', car_info_element)
    res_dict = dict(js_result)
    for (key,value) in res_dict.items():
        key = key.split("/")[0].strip().lower().replace(" ", "_")
        json[key] = value


def get_car_pricing(driver, json):
    try:
        kbb_price_xpath = '//div[@id="detailed-pricing1-app-root"]/' \
                          'dl[@class="pricing-detail line-height-condensed mb-4 inv-type-used"]/' \
                          'dd[@class="salePrice"]/' \
                          'span'
        kbb_price = driver.find_element(By.XPATH, kbb_price_xpath)
        json['kbb_price'] = str(kbb_price.text)
    except:
        pass
    finally:
        echopark_price_xpath = '//div[@id="detailed-pricing1-app-root"]/' \
                               'dl[@class="pricing-detail line-height-condensed mb-4 inv-type-used"]/' \
                               'dd[@class="final-price internetPrice font-weight-bold BLANK ddc-font-size-large line-height-reset pb-2 text-muted"]/' \
                               'span'
        echopark_price = driver.find_element(By.XPATH, echopark_price_xpath)
        json['echopark_price'] = str(echopark_price.text)


def get_car_location(driver, json):
    location_xpath = '//div[@class="d-sm-inline mr-5"]/strong'
    location = driver.find_element(By.XPATH, location_xpath)
    json['available_location'] = location.text.strip()


def get_car_other_specs(driver, json):
    specs_xpath = "//li[@class='spec-item']"
    specs = driver.find_elements(By.XPATH, specs_xpath)
    all_specs = []
    for spec in specs:
        js_result = driver.execute_script(
            'arr = arguments[0].getElementsByTagName("span");'
            'desc = "";'
            'detail = "";'
            'if (arr.length == 2){'
            'desc = arr[0].textContent;'
            'detail = arr[1].textContent;'
            '}'
            'else{'
            'detail = arr[0].textContent;'
            '}'
            'return [desc, detail];', spec)
        arr = list(js_result)
        key = arr[0].split(":")[0].strip()
        value = arr[1].strip()
        if key != "":
            new_key = key.lower().replace(" ", "_")
            json[new_key] = value
        all_specs.append(key+" : "+value)

    json["detailed_specs"] = all_specs


if __name__ == "__main__":
    driver = webdriver.Chrome(executable_path='/Users/sreekar/Downloads/chromedriver')
    urls = ["https://www.echopark.com/used/Toyota/2019-Toyota-Camry-d89774390a0e09a957961eee86975b44.htm"]

    result = []
    for url in urls:
        specs_dict = {}
        driver.get(url)

        get_car_identification(driver, specs_dict)
        get_car_basic_details(driver, specs_dict)
        get_car_pricing(driver, specs_dict)
        get_car_location(driver, specs_dict)
        get_car_other_specs(driver, specs_dict)
        # print(specs_dict)
        result.append(specs_dict)

    driver.quit()

    file = open("/Users/sreekar/Desktop/scrape.json", "w")
    json.dump(result, file)
    file.close()

    # json_obj = json.dumps(result)