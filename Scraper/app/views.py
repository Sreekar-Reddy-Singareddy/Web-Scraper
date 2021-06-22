from django.shortcuts import render
from django.http import HttpResponse
from app.web_scrape.echopark_scraper import scrape_car_details
from app.web_scrape.echopark_scraper import scrape_all_car_urls

# Create your views here.
def car_details (request):
    URL_KEY = "echopark-url"
    headers = request.headers
    if (URL_KEY in headers.keys()):
        url_to_scrape = headers[URL_KEY]
        result = scrape_car_details(url_to_scrape)
        return HttpResponse(str(result))
    else:
        return HttpResponse(str("INVALID REQUEST"))

def inventory_urls (req):
    echopark_all_inventory_url = "https://www.echopark.com/used-cars.htm"
    print("In inventory_urls method")
    result = scrape_all_car_urls(echopark_all_inventory_url)
    print("-----",result)
    return HttpResponse(str(result))

def tester (req):
    print("In tester method")
    return HttpResponse("Welcome to Web Scraper project. Check your URL.")

