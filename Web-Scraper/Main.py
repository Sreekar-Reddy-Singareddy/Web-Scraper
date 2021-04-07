import requests

def fun1():
    URL = 'https://www.monster.com/jobs/search/?q=Software-Developer&where=Australia'
    page = requests.get(URL)

    print(page)


if __name__ == "__main__":
    fun1()