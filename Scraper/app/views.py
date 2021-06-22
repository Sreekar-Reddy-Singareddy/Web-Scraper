from django.shortcuts import render
from django.http import HttpResponse
from app.parse.parser import scrape

# Create your views here.
def html_parser (request):
    print("In html_parser")
    URL_KEY = "echopark-url"
    url_to_scrape = None
    headers = request.headers
    if (URL_KEY in headers.keys()):
        url_to_scrape = headers[URL_KEY]
        result = scrape(url_to_scrape)
        return HttpResponse(str(result))
    else:
        return HttpResponse(str("INVALID REQUEST"))

def demo (req):
    print("In demo")
    return HttpResponse(str({"Name": "Sreekar", "Age": 25}))

