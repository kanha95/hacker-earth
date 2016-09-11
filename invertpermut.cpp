#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <cmath>
using namespace std;

typedef long long ll;
const ll MOD = (ll)1e9 + 7;
ll add(ll x, ll y)
{
	x += y;
	if (x >= MOD) return x - MOD;
	return x;
}
ll sub(ll x, ll y)
{
	x -= y;
	if (x < 0) return x + MOD;
	return x;
}
ll mult(ll x, ll y)
{
	return (x * y) % MOD;
}
ll bin_pow(ll x, ll p)
{
	if (p == 0) return 1;
	if (p == 2 || (p & 1)) return mult(x, bin_pow(x, p - 1));
	return bin_pow(bin_pow(x, p / 2), 2);
}
ll rev(ll x)
{
	return bin_pow(x, MOD - 2);
}

const int K = 101000;
ll a[K];
ll revs[K];

void precalc()
{
	a[0] = 1;
	for (int q = 1; (3 * q * q + q) / 2 < K; q++)
	{
		a[(3 * q * q + q) / 2] = a[(3 * q * q - q) / 2] = (q & 1 ? MOD - 1 : 1);
	}
	for (int i = 1; i < K; i++)
		revs[i] = rev(i);
}

int main()
{
	precalc();
	
	int t;
	scanf("%d", &t);
	while(t--)
	{
		ll n, k;
		scanf("%lld%lld", &n, &k);
		//if (k >= K) throw;
		ll ans = 0;
		ll C = 1;
		for (int i = 0; i <= k; i++)
		{
			ans = add(ans, mult(C, a[k - i]));
			C = mult(C, n + i);
			C = mult(C, revs[i + 1]);
		}
		printf("%lld\n", ans);
	}
	
    return 0;
}
