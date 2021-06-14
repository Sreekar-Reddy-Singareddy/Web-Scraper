from pathy import file
from selenium import webdriver
from selenium.webdriver import Chrome
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium import *
from selenium.webdriver.support import expected_conditions as EC

def parse ():
    return {"Brand": "Honda", "Model": "Accord"}


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
        final_spec = key + " : " + value
        all_specs.append(final_spec)
    json["detailed_specs"] = all_specs


if __name__ == "__main__":
    driver = webdriver.Chrome(executable_path='/Users/sreekar/Downloads/chromedriver')
    print("First URL loading...")

    urls = ["https://www.echopark.com/used/Honda/2019-Honda-Civic-b3e89b230a0e0ae86ba3e77bd4884a88.htm",
            "https://www.echopark.com/used/Honda/2018-Honda-Accord-283ecd300a0e0a173e8f42ae7f8a180b.htm"]

    result = []
    for url in urls:
        specs_dict = {}
        driver.get(url)

        get_car_identification(driver, specs_dict)
        get_car_basic_details(driver, specs_dict)
        get_car_pricing(driver, specs_dict)
        get_car_location(driver, specs_dict)
        get_car_other_specs(driver, specs_dict)
        print(specs_dict)
        result.append(specs_dict)

    driver.quit()
    f = open("/Users/sreekar/Desktop/scrape.json", "a")
    f.write(str(result))
    f.close()